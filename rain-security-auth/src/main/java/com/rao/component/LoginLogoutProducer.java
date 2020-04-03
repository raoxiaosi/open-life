package com.rao.component;

import com.rao.constant.user.OperationTypeEnum;
import com.rao.dto.IpInfo;
import com.rao.pojo.bo.UserExtend;
import com.rao.pojo.bo.UserLoginLogoutLogBO;
import com.rao.util.common.CopyUtil;
import com.rao.util.common.UserAgentUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author : hudelin
 * @className :LogInLogoutProducer
 * @description : 登录登出mq生产者
 * @date: 2019-12-31 10:59
 */
@Component
public class LoginLogoutProducer {

    private final static String LOG_TOPIC = "LoginLogout";

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送登出消息
     */
    public void sendLogMsg(OperationTypeEnum operationType) {
        // 获取请求的ip等信息
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        IpInfo ipInfo = UserAgentUtils.getIpInfo(UserAgentUtils.getIpAddr(request));
        UserLoginLogoutLogBO userLoginLogoutLogBO = CopyUtil.transToObj(ipInfo, UserLoginLogoutLogBO.class);
        userLoginLogoutLogBO.setProvince(ipInfo.getRegion());
        // 获取当前用户信息
        getCurrentUserInfo(userLoginLogoutLogBO);
        // 设置用户操作类型
        userLoginLogoutLogBO.setType(operationType.getValue());
        userLoginLogoutLogBO.setSendTime(new Date());
        rocketMQTemplate.convertAndSend(LOG_TOPIC, userLoginLogoutLogBO);
    }

    /**
     * 发送登录消息
     * @param currentUserInfo
     */
    public void sendLogMsg(UserLoginLogoutLogBO currentUserInfo, OperationTypeEnum operationType){
        // 获取请求的ip等信息
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        IpInfo ipInfo = UserAgentUtils.getIpInfo(UserAgentUtils.getIpAddr(request));
        UserLoginLogoutLogBO userLoginLogoutLogBO = CopyUtil.transToObj(ipInfo, UserLoginLogoutLogBO.class);
        userLoginLogoutLogBO.setProvince(ipInfo.getRegion());
        // 获取当前用户信息
        userLoginLogoutLogBO.setUserId(currentUserInfo.getUserId());
        userLoginLogoutLogBO.setUserName(currentUserInfo.getUserName());
        userLoginLogoutLogBO.setPhone(currentUserInfo.getPhone());
        userLoginLogoutLogBO.setUserType(currentUserInfo.getUserType());
        userLoginLogoutLogBO.setWxOpenid(currentUserInfo.getWxOpenid());
        userLoginLogoutLogBO.setType(operationType.getValue());
        userLoginLogoutLogBO.setSendTime(new Date());
        rocketMQTemplate.convertAndSend(LOG_TOPIC, userLoginLogoutLogBO);
    }


    /**
     * 获取当前用户的信息
     * @return
     */
    private UserLoginLogoutLogBO getCurrentUserInfo(UserLoginLogoutLogBO userLoginLogoutLogBO){
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
        UserExtend userExtend = (UserExtend)auth2Authentication.getUserAuthentication().getPrincipal();
        // 设置用户的基本信息
        userLoginLogoutLogBO.setUserId(userExtend.getId());
        userLoginLogoutLogBO.setUserName(userExtend.getName());
        userLoginLogoutLogBO.setPhone(userExtend.getPhone());
        userLoginLogoutLogBO.setUserType(userExtend.getUserType());
        userLoginLogoutLogBO.setWxOpenid(userExtend.getWxOpenid());
        return userLoginLogoutLogBO;
    }

}
