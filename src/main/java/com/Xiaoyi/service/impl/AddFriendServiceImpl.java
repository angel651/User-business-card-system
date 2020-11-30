package com.Xiaoyi.service.impl;

import com.Xiaoyi.common.Constant;
import com.Xiaoyi.common.ResponseEnum;
import com.Xiaoyi.common.ServerResponse;
import com.Xiaoyi.entity.AddFriendList;
import com.Xiaoyi.entity.FriendList;
import com.Xiaoyi.entity.User;
import com.Xiaoyi.repository.AddFriendListRepository;
import com.Xiaoyi.repository.FriendListRepository;
import com.Xiaoyi.repository.UserRepository;
import com.Xiaoyi.service.AddFriendService;
import com.Xiaoyi.vo.AddFriendVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：Xiaoyi
 * @Date ：Created in 14:16 2020/12/5
 * @Description：请求添加好友的接口
 * @Modified By：
 * @Version: 1.0
 */
@Slf4j
@Service
public class AddFriendServiceImpl implements AddFriendService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddFriendListRepository addFriendListRepository;

    @Autowired
    FriendListRepository friendListRepository;
    @Override
    public ServerResponse addFriendReq(AddFriendList addFriendList) {
        long sendUserId = addFriendList.getSendUserId();
        long receiveUserId = addFriendList.getReceiveUserId();
        //是否为好友
        if (friendListRepository.isFriend(sendUserId, receiveUserId) > 0){
            return ServerResponse.getInstance().code(200).message("请勿重复添加");
        }
        //好友请求已存在
        if(null != addFriendListRepository.findBySendUserIdAndReceiveUserIdAndIsReceive(sendUserId,
                receiveUserId, 0)){
            return ServerResponse.getInstance().code(200).message("请等待对方同意");
        }
        addFriendList.setSendTime(new Date());
        try {
            //1、存入数据库
            addFriendListRepository.save(addFriendList);
            //2、消息推送
            return ServerResponse.getInstance().code(200).message("发送成功");
        }catch (Exception e){
            e.printStackTrace();
            log.info(Constant.TAG_NEGEN + e.getMessage());
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse handleAddFriend(Long addFriendId, Integer isReceive) {
        try {
            AddFriendList addFriendList = addFriendListRepository.findById(addFriendId).get();
            if (null == addFriendList){
                return ServerResponse.getInstance()
                        .code(200)
                        .message("好友请求不存在");
            }

            //修改添加好友请求状态
            addFriendList.setIsReceive(isReceive);
            Long sendUserId = addFriendList.getSendUserId();
            Long receiveUserId = addFriendList.getReceiveUserId();

            User sendUser = userRepository.getOne(sendUserId);
            User receiveUser = userRepository.getOne(receiveUserId);

            //互相新增入好友列表
            List<FriendList> friendLists = new ArrayList<FriendList>();
            FriendList friendListSend = new FriendList();
            friendListSend.setUserId(sendUserId);
            friendListSend.setFriendUserId(receiveUserId);
            friendListSend.setFriendNickName(receiveUser.getUserName());
            friendLists.add(friendListSend);

            FriendList friendListReceive = new FriendList();
            friendListReceive.setUserId(receiveUserId);
            friendListReceive.setFriendUserId(sendUserId);
            friendListReceive.setFriendNickName(sendUser.getUserName());
            friendLists.add(friendListReceive);
            friendListRepository.saveAll(friendLists);

            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.ADD_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            log.info(Constant.TAG_NEGEN + e.getMessage());
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    @Override
    public ServerResponse listAddFriendReq(Long userId) {
        try{
            List<Object[]> addFriendLists =
//                    addFriendListRepository.findByReceiveUserIdAndIsReceiveOrderBySendTimeDesc(userId, 0);
                    addFriendListRepository.selectByReceiveUserIdAndIsReceiveOrderBySendTimeDesc(userId);
            List<AddFriendVo> addFriendVos = new ArrayList<>();
            addFriendLists.forEach(row -> {
                AddFriendVo addFriendVo = new AddFriendVo();
                String userName = (String)row[0];
                Integer sex = (Integer)row[1];
                String avatar = (String)row[2];
                String message = (String)row[3];
//                Boolean is_receive = (Boolean)row[4];
                Date send_time = (Date)row[5];
                BigInteger id = (BigInteger)row[6];
                addFriendVo.setUserName(userName);
                addFriendVo.setSex(sex);
                addFriendVo.setAvatar(avatar);
                addFriendVo.setMessage(message);
                addFriendVo.setSendTime(send_time);
                addFriendVo.setId(id.intValue());
                addFriendVos.add(addFriendVo);
            });
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(addFriendVos);
        }catch (Exception e){
            e.printStackTrace();
            log.info(Constant.TAG_NEGEN + e.getMessage());
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
