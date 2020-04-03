package com.rao.service;

/**
 * 会员钱包 service
 * @author raojing
 * @date 2020/1/9 10:04
 */
public interface RainMemberWalletService {

    /**
     * 初始化会员钱包信息
     * @param userId
     */
    void initWallet(Long userId);
    
}
