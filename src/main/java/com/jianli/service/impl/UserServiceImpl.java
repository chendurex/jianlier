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

    @Transactional
    @Override
    public ResResult getInfoByOpenid(String openid) {
        User origin = userRepo.get(openid);
        UserVO vo = BeanUtils.copy(origin, UserVO.class);
        vo.setResumeId(resumeService.getResumeIdByUid(origin.getId()));
        // todo 验证下，如果超过一定的时间则重新获取用户信息
        return ResUtils.data(vo);
    }

    @Override
    public User submit(String code, String state) {
        log.info("获取到用户code：{}， state：{}", code, state);
        /*UserParam param = authInvoker.getAccessToken(code, state);
        log.info("获取到用户登录信息，param:{}", param);
        UserParam info = authInvoker.getUserInfo(param.getAccessToken(), param.getOpenid());
        log.info("获取到用户信息，info:{}", info);
        User user = User.builder().nickname(info.getNickname()).headImgUrl(info.getHeadImgUrl()).country(info.getCountry())
                .province(info.getProvince()).city(info.getCity()).sex(info.getSex()).unionId(info.getUnionId())
                .accessToken(param.getAccessToken()).expiresIn(param.getExpiresIn())
                .openid(param.getOpenid()).refreshToken(param.getRefreshToken()).scope(param.getScope())
                .build();

        User origin = userRepo.get(user.getOpenid());
        User real;
        if (origin == null) {
            user.submit();
            real = userRepo.save(user);
        } else {
            user.modify();
            user.setId(origin.getId());
            real = userRepo.save(user);
        }*/

        return null;
    }

    @Override
    public boolean isOwner(int id, String openid) {
        Optional<User> user = userRepo.findById(id);
        return user.filter(u -> openid.equals(u.getOpenid())).isPresent();
    }
}
