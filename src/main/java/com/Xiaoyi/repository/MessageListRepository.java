package com.Xiaoyi.repository;

import com.Xiaoyi.entity.MessageList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ：Xiaoyi
 * @Date ：Created in 21:02 2020/12/4
 * @Description：
 * @Modified By：
 * @Version: 1.0
 */
public interface MessageListRepository extends JpaRepository<MessageList, Long>, JpaSpecificationExecutor {
}
