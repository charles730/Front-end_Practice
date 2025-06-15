package com.neusoft.elm.controller;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.po.Business;
import com.neusoft.elm.po.User;
import com.neusoft.elm.service.BusinessService;
import com.neusoft.elm.service.UserService;
import com.neusoft.elm.service.impl.BusinessServiceImpl;
import com.neusoft.elm.service.impl.UserServiceImpl;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/UserController")
public class UserController {
    @PostMapping("/getUserByIdByPass")
    public Object getUserByIdByPass(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        UserService service = new UserServiceImpl();
        return service.getUserByIdByPass(userId, password);
    }

    @PostMapping("/getUserById")
    public Object getUserById(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        UserService service = new UserServiceImpl();
        return service.getUserById(userId);
    }

    @PostMapping("/saveUser")
    public Object saveUser(HttpServletRequest request) throws Exception {
        User user = new User();
        user.setUserId(request.getParameter("userId"));
        user.setPassword(request.getParameter("password"));
        user.setUserName(request.getParameter("userName"));
        user.setUserSex(Integer.valueOf(request.getParameter("userSex")));
        UserService service = new UserServiceImpl();
        return service.saveUser(user);
    }

    @PostMapping("/listStarBusinessById")
    public Object listStarBusinessById(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        BusinessService service = new BusinessServiceImpl();
        return service.listStarBusinessById(userId);
    }
}