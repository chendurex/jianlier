package com.jianli.service.impl;

import com.jianli.domain.User;
import com.jianli.dto.UserParam;
import com.jianli.repo.UserRepo;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.UserService;
import com.jianli.wechat.AuthInvoker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chendurex
 * @date 2018-07-22 09:37
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final AuthInvoker authInvoker;
    public UserServiceImpl(UserRepo userRepo, AuthInvoker authInvoker) {
        this.userRepo = userRepo;
        this.authInvoker = authInvoker;
    }

    @Override
    public ResResult submit(String code, String state) {
        log.info("获取到用户code：{}， state：{}", code, state);
        UserParam param = authInvoker.getAccessToken(code, state);
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
            real = userRepo.save(user);
        }

        if (real.getId() == null) {
            return ResUtils.fail("保存数据失败");
        }
        return ResUtils.data(user);
    }
}
