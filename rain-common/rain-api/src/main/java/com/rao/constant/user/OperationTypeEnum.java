package com.rao.constant.user;

import com.rao.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作类型枚举
 * @author raojing
 * @date 2019/12/7 23:27
 */
@AllArgsConstructor
public enum OperationTypeEnum {

    /**
     * 密码登录
     */
    LOG_IN_PWD(1),

    /**
     * 短信验证码登录
     */
    LOG_IN_SMS_CODE(2),

    /**
     * 微信第三方登录
     */
    LOG_IN_WX(3),

    /**
     * 正常登出
     */
    LOG_OUT(10),

    /**
     * 强制登出
     */
    FORCE_LOG_OUT(11),
    ;

    /**
     * 用户类型
     */
    @Getter
    private Integer value;

}
