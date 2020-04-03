package com.rao.controller;

import com.rao.annotation.SimpleValid;
import com.rao.pojo.bo.CurrentUserInfo;
import com.rao.service.RainMemberWalletService;
import com.rao.util.result.ResultMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 会员钱包 controller
 * @author raojing
 * @date 2020/1/9 10:06
 */
@RestController
@RequestMapping("/member/wallet")
public class RainMemberWalletController {
    
    @Resource
    private RainMemberWalletService rainMemberWalletService;

    /**
     * 初始化会员钱包
     * 因为这个接口没有进行权鉴，防止恶意访问造成数据紊乱
     * 从缓存中获取用户注册信息，再进行初始化操作，保证数据安全性
     * @param userId
     * @return
     */    
    @PostMapping("/init")
    public ResultMessage init(@SimpleValid @NotNull(message = "用户id不能为空") @RequestParam("userId") Long userId){
        rainMemberWalletService.initWallet(userId);
        return ResultMessage.success().message("初始化会员钱包成功");
    }    
    
}
