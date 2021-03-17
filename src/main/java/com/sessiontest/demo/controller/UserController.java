package com.sessiontest.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    //读取session
    @GetMapping("/get")
    public String getsess(HttpServletRequest request) {
        HttpSession session=request.getSession();
        String username = (String)session.getAttribute("username");
        System.out.println("session username:"+username);
        if (username == null) {
            return "";
        } else {
            return username;
        }
    }

    //设置session
    @GetMapping("/set")
    public String setSess(@RequestParam("userName")String userName, HttpServletRequest request) {
        HttpSession session=request.getSession();
        session.setAttribute("username", userName);
        return userName;
    }
}
