package com.rao.component.listener;

import com.rao.dao.UserLoginLogoutLogDao;
import com.rao.pojo.bo.UserLoginLogoutLogBO;
import com.rao.pojo.entity.UserLoginLogoutLog;
import com.rao.util.common.CopyUtil;
import com.rao.util.common.TwiterIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : hudelin
 * @className :LoginLogoutListener
 * @description : 登录登出监听器
 * @date: 2019-12-31 11:03
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "LoginLogout", consumerGroup = "LoginLogoutGroup")
public class LoginLogoutListener implements RocketMQListener<UserLoginLogoutLogBO> {

    @Resource
    private UserLoginLogoutLogDao userLoginLogoutLogDao;

    @Override
    public void onMessage(UserLoginLogoutLogBO userLoginLogoutLogBO) {
        log.info("监听到消息:{}", userLoginLogoutLogBO);
        UserLoginLogoutLog userLoginLogoutLog = CopyUtil.transToObj(userLoginLogoutLogBO, UserLoginLogoutLog.class);
        userLoginLogoutLog.setId(TwiterIdUtil.getTwiterId());
        userLoginLogoutLog.setCreateTime(userLoginLogoutLogBO.getSendTime());
        userLoginLogoutLogDao.insertSelective(userLoginLogoutLog);
    }
}
