package com.shop.core.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/showName")
    public Map<String,Object> showName(HttpServletRequest request)
    {
        SecurityContext spring_security_context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        //拿出用户对象
        User user = (User) spring_security_context.getAuthentication().getPrincipal();
        //获取用户中的用户名字
        Map<String,Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("curTime",new Date());
        return map;
    }
}
