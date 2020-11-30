package com.Xiaoyi.controller;

import com.Xiaoyi.common.ServerResponse;
import com.Xiaoyi.entity.User;
import com.Xiaoyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Xiaoyi
 * @Date ：Created in 17:20 2020/12/3
 * @Description：用户控制类
 * @Modified By：
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    UserService userServiceImpl;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    ServerResponse userRegister(@RequestBody User user){
        return userServiceImpl.userRegister(user);
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    ServerResponse userLogin(@RequestBody User user){
        return userServiceImpl.userLogin(user);
    }
}
