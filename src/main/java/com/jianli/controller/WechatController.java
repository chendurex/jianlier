package com.jianli.controller;

import com.jianli.commons.StringUtils;
import com.jianli.response.ResResult;
import com.jianli.service.UserService;
import com.jianli.wechat.AuthInvoker;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class WechatController {

    private final AuthInvoker invoker;
    private final UserService userService;

    public WechatController(AuthInvoker invoker, UserService userService) {
        this.invoker = invoker;
        this.userService = userService;
    }

    @GetMapping(value = "/login")
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

    @GetMapping(value = "/callback")
    public ResResult callback(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state) {
        return userService.submit(code, state);
        // 需要回调前段页面
    }
}
