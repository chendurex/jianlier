package com.jianli.service.impl;

import com.jianli.commons.BeanUtils;
import com.jianli.domain.User;
import com.jianli.dto.UserParam;
import com.jianli.dto.UserVO;
import com.jianli.repo.UserRepo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.ResumeService;
import com.jianli.service.UserService;
import com.jianli.wechat.AuthInvoker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author chendurex
 * @date 2018-07-22 09:37
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final AuthInvoker authInvoker;
    private final ResumeService resumeService;
    public UserServiceImpl(UserRepo userRepo, AuthInvoker authInvoker, ResumeService resumeService) {
        this.userRepo = userRepo;
        this.authInvoker = authInvoker;
        this.resumeService = resumeService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResResult getInfoByTicket(String ticket) {
        User origin = userRepo.getByAccessToken(ticket);
        if (origin == null) {
            return ResUtils.fail("您的凭证已过期，请重新登录");
        }
        // todo 不需要主动刷新，让客户端调其它接口被动刷新
        if (!isOwner(origin.getId(), origin.getAccessToken())) {
            UserParam info = authInvoker.refreshAccessToken(origin.getRefreshToken());
            userRepo.refreshToken(info.getAccessToken(), (int)(System.currentTimeMillis()/1000) + info.getExpiresIn(), origin.getId());
            origin.setAccessToken(info.getAccessToken());
        }
        /*if (isOwner(origin.getId(), origin.getAccessToken())) {
            throw new AuthenticException();
        }*/
        UserVO vo = BeanUtils.copy(origin, UserVO.class);
        vo.setResumeId(resumeService.getResumeIdByUid(origin.getId()));
        return ResUtils.data(vo);
    }

    @Override
    public ResResult refreshTicket(String ticket) {
        User origin = userRepo.getByAccessToken(ticket);
        if (origin == null) {
            return ResUtils.fail("您的凭证已过期，请重新登录");
        }
        //todo 判断下，如果没有过期，不用调微信接口刷新，直接返回原值
        UserParam info = authInvoker.refreshAccessToken(origin.getRefreshToken());
        userRepo.refreshToken(info.getAccessToken(), (int)(System.currentTimeMillis()/1000) + info.getExpiresIn(), origin.getId());
        return ResUtils.data(info.getAccessToken());
    }

    @Override
    public User submit(String code, String state) {
        log.info("获取到用户code：{}， state：{}", code, state);
        UserParam param = authInvoker.getAccessToken(code, state);
        log.info("获取到用户登录信息，param:{}", param);
        UserParam info = authInvoker.getUserInfo(param.getAccessToken(), param.getOpenid());
        log.info("获取到用户信息，info:{}", info);
        User user = User.builder().nickname(info.getNickname()).headImgUrl(info.getHeadImgUrl()).country(info.getCountry())
                .province(info.getProvince()).city(info.getCity()).sex(info.getSex()).unionId(info.getUnionId())
                .accessToken(param.getAccessToken()).expiresIn(param.getExpiresIn())
                .openid(param.getOpenid()).refreshToken(param.getRefreshToken()).scope(param.getScope())
                .expiresTime((int)(System.currentTimeMillis()/1000) + param.getExpiresIn())
                .build();

        User origin = userRepo.getByOpenid(user.getOpenid());
        User real;
        if (origin == null) {
            user.submit();
            real = userRepo.save(user);
        } else {
            user.modify();
            user.setId(origin.getId());
            real = userRepo.save(user);
        }
        return real;
    }

    @Override
    public boolean isOwner(int id, String ticket) {
        Optional<User> user = userRepo.findById(id);
        return user
                .filter(u -> ticket.equals(u.getAccessToken()))
                .filter(u ->u.getExpiresTime() > (System.currentTimeMillis()/1000))
                .isPresent();
    }
}
