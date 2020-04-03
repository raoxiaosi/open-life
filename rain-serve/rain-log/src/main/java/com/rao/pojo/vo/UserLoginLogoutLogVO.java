package com.rao.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 系统用户登录登出日志
 * @author raojing
 * @date 2019/12/29 23:13
 */
@Data
public class UserLoginLogoutLogVO {

    /**
     * 类型 1-登录 2-登出
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
     * 互联网服务提供商
     */
    private String isp;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

}
