package com.rao.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 微信第三方登录
 * @author raojing
 * @date 2020/1/5 2:33
 */
@Data
public class WxLoginDTO {

    /**
     * code
     */
    @NotBlank(message = "code不能为空")
    private String code;

    /**
     * 账号类型
     */
    @NotBlank(message = "账号类型不能为空")
    private String accountType;

}
