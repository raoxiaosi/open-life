package com.rao.service;

import com.rao.dto.WxUserInfo;
import com.rao.pojo.bo.LoginUserBO;
import com.rao.pojo.dto.SmsSendDTO;
import com.rao.pojo.entity.RainMember;

/**
 * 用户 service
 * @author raojing
 * @date 2019/12/7 23:39
 */
public interface UserService {

    /**
     * 通过用户名或手机号码，用户类型查询用户信息
     * @param userName
     * @param type
     * @return
     */
    LoginUserBO findByAccountAndUserType(String userName, String type);

    /**
     * 通过openID，用户类型查询用户信息
     * @param openId
     * @param accountType
     * @return
     */
    LoginUserBO findByOpenIdAndUserType(String openId, String accountType);

    /**
     * 注册c端用户信息
     * @param wxOpenId
     * @return
     */
    RainMember registerMember(String wxOpenId);

    /**
     * 检查账号状态
     * @param smsSendDTO
     */
    void checkAccount(SmsSendDTO smsSendDTO);

}
