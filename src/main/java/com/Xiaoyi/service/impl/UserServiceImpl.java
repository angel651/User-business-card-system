package com.Xiaoyi.service.impl;

import com.Xiaoyi.common.Constant;
import com.Xiaoyi.common.ResponseEnum;
import com.Xiaoyi.common.ServerResponse;
import com.Xiaoyi.entity.User;
import com.Xiaoyi.repository.UserRepository;
import com.Xiaoyi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author ：Negen
 * @Date ：Created in 17:30 2020/12/3
 * @Description：用户业务层
 * @Modified By：
 * @Version: 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public ServerResponse userRegister(User user) {
        try {
            if (StringUtils.isEmpty(user.getUserName())
                    || StringUtils.isEmpty(user.getPassword())
//                    || StringUtils.isEmpty(user.getAvatar())
                    || StringUtils.isEmpty(user.getSex())){
                //参数不完整
                return ServerResponse.getInstance()
                        .responseEnum(ResponseEnum.INVALID_PARAM);
            }
            User cUser = userRepository.findByUserName(user.getUserName());
            if(cUser != null){
                //用户名已存在
                return ServerResponse.getInstance()
                        .responseEnum(ResponseEnum.USERNAME_EXSIT);
            }
            userRepository.save(user);
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.REGISTE_SUCCESS);
        }catch (Exception e){
            log.info(Constant.TAG_NEGEN + e.getMessage());
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public ServerResponse userLogin(User user) {
        try {
            if(StringUtils.isEmpty(user.getUserName())
                    || StringUtils.isEmpty(user.getPassword())){
                return ServerResponse.getInstance()
                        .responseEnum(ResponseEnum.INVALID_PARAM);
            }
            User cUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
            if(cUser == null){
                return ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_FAILED);
            }
            cUser.setPassword("");
            //登陆成功返回用户信息
            return ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_SUCCESS).data(cUser);
        }catch (Exception e){
            log.info(Constant.TAG_NEGEN + e.getMessage());
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
