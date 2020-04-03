package com.rao.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : hudelin
 * @className :UserLoginLogoutDTO
 * @description : 用户登录登出日志dto
 * @date: 2020-01-13 14:34
 */
@Data
public class UserLoginLogoutLogDTO {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 账号类型
     */
    @NotBlank(message = "账号类型不能为空")
    private String accountType;
}
