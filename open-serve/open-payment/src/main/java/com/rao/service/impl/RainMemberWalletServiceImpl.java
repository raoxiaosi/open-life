package com.rao.service.impl;
import java.util.Date;

import com.rao.cache.key.UserCacheKey;
import com.rao.dao.RainMemberWalletDao;
import com.rao.pojo.entity.RainMemberWallet;
import com.rao.service.RainMemberWalletService;
import com.rao.util.cache.RedisTemplateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 会员钱包 service 实现
 * @author raojing
 * @date 2020/1/9 10:04
 */
@Service
public class RainMemberWalletServiceImpl implements RainMemberWalletService {
    
    @Resource
    private RainMemberWalletDao rainMemberWalletDao;
    @Resource
    private RedisTemplateUtils redisTemplateUtils;

    @Override
    public void initWallet(Long userId) {
        // 从缓存中获取该用户的注册记录
        String userRegisterCacheKey = UserCacheKey.userRegisterCacheKey(userId);
        if(!redisTemplateUtils.isExists(userRegisterCacheKey)){
            // 如果不存在，不再进行初始化操作
            return;
        }
        Date now = new Date();
        RainMemberWallet memberWallet = new RainMemberWallet();
        memberWallet.setMemberId(userId);
        memberWallet.setPayPassword("");
        memberWallet.setBalance(0L);
        memberWallet.setHistoryBalance(0L);
        memberWallet.setCreateTime(now);
        memberWallet.setUpdateTime(now);
        rainMemberWalletDao.insertSelective(memberWallet);
    }
}
