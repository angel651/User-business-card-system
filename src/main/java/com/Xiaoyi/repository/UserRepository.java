package com.Xiaoyi.repository;

import com.Xiaoyi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Xiaoyi
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查询账号是否存在
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 根据用户名+密码查询账号是否存在
     * @param userName
     * @param password
     * @return
     */
    User findByUserNameAndPassword(String userName, String password);
}
