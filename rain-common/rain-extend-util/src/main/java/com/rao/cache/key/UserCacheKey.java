package com.rao.cache.key;

/**
 * 用户模块缓存健
 * @author raojing
 * @date 2020/1/10 15:44
 */
public class UserCacheKey {

    /**
     * 构建用户注册缓存健
     * @param userId
     * @return
     */
    public static String userRegisterCacheKey(Long userId){
        return "user:register:" + userId;
    } 
    
}
