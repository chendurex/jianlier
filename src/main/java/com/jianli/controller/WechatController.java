package com.jianli.controller;

import com.jianli.commons.StringUtils;
import com.jianli.domain.User;
import com.jianli.dto.UserParam;
import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import com.jianli.service.UserService;
import com.jianli.wechat.AuthInvoker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chendurex
 * @date 2018-07-22 08:36
 */
@RestController
@RequestMapping("/wechat")
@CrossOrigin(origins = "*")
@Api(description = "微信登录模块")
@Slf4j
public class WechatController {

    private final AuthInvoker invoker;
    private final UserService userService;
    private final String mainUrl;
    public WechatController(AuthInvoker invoker, UserService userService, @Value("${main.url}") String mainUrl) {
        this.invoker = invoker;
        this.userService = userService;
        this.mainUrl = mainUrl;
    }

    @GetMapping(value = "/login")
    @ApiOperation(value = "微信页面登录", hidden = true)
    public void login(HttpServletResponse response) {
        String url = invoker.redirect();
        if (StringUtils.isEmpty(url)) {
            log.error("获取跳转到微信的URL失败");
        }
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    @ApiOperation(value = "获取微信登录基本信息", response = ResResult.class)
    @GetMapping(value = "/internal/login")
    public ResResult login() {
        return ResUtils.data(invoker.getWechatParam());
    }

    @ApiOperation(value = "根据openid获取用户信息", response = ResResult.class)
    @GetMapping(value = "/getInfo")
    public ResResult genInfo(@RequestParam(value = "ticket")
                           @ApiParam(name = "ticket", value = "ticket", example = "of0EF1tJkDVdHOTp4xI9iun9bE")String ticket) {
        return userService.getInfoByTicket(ticket);
    }

    @ApiOperation(value = "刷新ticket", response = ResResult.class)
    @GetMapping(value = "/refresh/ticket")
    public ResResult refreshTicket(@RequestParam(value = "ticket")
                                   @ApiParam(name = "ticket", value = "ticket", example = "of0EF1tJkDVdHOTp4xI9iun9bE")String ticket) {
        return userService.refreshTicket(ticket);
    }

    @ApiOperation(value = "微信登录后的回调", hidden = true)
    @GetMapping(value = "/callback")
    public void callback(HttpServletResponse response,
                              @RequestParam(value = "code") String code,
                              @RequestParam(value = "state") String state) {
        User user = userService.submit(code, state);
        if (user == null) {
            log.error("用户数据回调失败");
            return ;
        }
        String url = mainUrl + "?type=1&ticket=" + user.getAccessToken();
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            log.error("回调给前段页面失败", e);
        }
    }
}
