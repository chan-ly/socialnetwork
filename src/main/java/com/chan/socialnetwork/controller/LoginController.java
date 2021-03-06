package com.chan.socialnetwork.controller;

import com.chan.socialnetwork.service.UserService;
import com.chan.socialnetwork.util.SocialnetworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(path = {"/reg/"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam(value = "remember", defaultValue = "0") int remember,
                           HttpServletResponse response){
        try {
            Map<String, Object> map = userService.register(username, password);
            if(map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if(remember > 0){
                    cookie.setMaxAge(3600 * 24 * 5);
                }
                response.addCookie(cookie);
                return SocialnetworkUtil.getJSONString(1, "注册成功");
            }else {
                return SocialnetworkUtil.getJSONString(1, map);
            }
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            return SocialnetworkUtil.getJSONString(1, "注册异常");
        }
    }

    @RequestMapping(path = {"/login/"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String login(Model model, @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "remember", defaultValue = "0") int remember,
                        HttpServletResponse response){
        try {
            Map<String, Object> map = userService.login(username, password);
            if(map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if(remember > 0){
                    cookie.setMaxAge(3600 * 24 * 5);
                }
                response.addCookie(cookie);
                return SocialnetworkUtil.getJSONString(0, "登录成功");
            }else {
                return SocialnetworkUtil.getJSONString(1, map);
            }
        }catch (Exception e){
            logger.error("登录异常" + e.getMessage());
            return SocialnetworkUtil.getJSONString(0, "登录异常");
        }
    }
}
