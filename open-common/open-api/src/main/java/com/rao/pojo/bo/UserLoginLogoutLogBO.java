package com.rao.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : hudelin
 * @className :UserLoginLogoutLogBO
 * @description :系统用户登录登出日志
 * @date: 2020-01-07 11:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginLogoutLogBO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 操作类型
     */
    private Integer type;

    /**
     * ip
     */
    private String ip;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String area;

    /**
     * 互联网服务提供商
     */
    private String isp;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 微信openID
     */
    private String wxOpenid;
}
