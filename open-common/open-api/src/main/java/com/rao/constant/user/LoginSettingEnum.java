package com.rao.constant.user;

import com.rao.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录配置 枚举
 * @author raojing
 * @date 2020/1/11 21:26
 */
@AllArgsConstructor
public enum LoginSettingEnum {

    /**
     * 后台用户密码登录
     */
    admin_pwd_login(UserTypeEnum.ADMIN.getValue(), OperationTypeEnum.LOG_IN_PWD.getValue(), true),

    /**
     * 后台用户短信验证码登录
     */
    admin_sms_code_login(UserTypeEnum.ADMIN.getValue(), OperationTypeEnum.LOG_IN_SMS_CODE.getValue(), true),

    /**
     * 后台用户微信第三方登录
     */
    admin_wx_login(UserTypeEnum.ADMIN.getValue(), OperationTypeEnum.LOG_IN_WX.getValue(), false),

    /**
     * c端用户密码登录
     */
    c_user_pwd_login(UserTypeEnum.C_USER.getValue(), OperationTypeEnum.LOG_IN_PWD.getValue(), false),

    /**
     * c端用户短信验证码登录
     */
    c_user_sms_code_login(UserTypeEnum.C_USER.getValue(), OperationTypeEnum.LOG_IN_SMS_CODE.getValue(), false),

    /**
     * c端用户微信第三方登录
     */
    c_user_wx_login(UserTypeEnum.C_USER.getValue(), OperationTypeEnum.LOG_IN_WX.getValue(), true),

    ;
    /**
     * 用户类型
     */
    @Getter
    private String userType;

    /**
     * 操作类型
     */
    @Getter
    private Integer operationType;

    /**
     * 是否开放
     */
    @Getter
    private Boolean open;

    public static boolean checkOpen(UserTypeEnum userType, OperationTypeEnum operationType){
        for (LoginSettingEnum item : values()) {
            if(item.getUserType().equals(userType.getValue()) && item.getOperationType().equals(operationType.getValue())){
                return item.open;
            }
        }
        throw BusinessException.operate("暂不支持该操作");
    }

}
