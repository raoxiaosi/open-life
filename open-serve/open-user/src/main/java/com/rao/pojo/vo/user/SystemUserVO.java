package com.rao.pojo.vo.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName : SystemUserVO  //类名
 * @Description : 平台用户相关视图  //描述
 * @Author : xujianjie  //作者
 * @Date: 2019-12-16 17:28  //时间
 */
@Data
public class SystemUserVO {

    /**
     * 用户id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态 1-启用 2-禁用 3-锁定 4-删除
     */
    private Integer status;

    /**
     * 注册时间
     */
    private Date createTime;

}
