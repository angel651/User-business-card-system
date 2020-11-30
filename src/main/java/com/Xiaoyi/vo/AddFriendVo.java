package com.Xiaoyi.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author ：Xiaoyi
 * @Date ：Created in 18:58 2020/12/6
 * @Description：
 * @Modified By：
 * @Version: 1.0
 */
@Data
public class AddFriendVo {
    Integer id;
    Date sendTime;
    String message;
    String avatar;
    Integer sex;
    String userName;
}
