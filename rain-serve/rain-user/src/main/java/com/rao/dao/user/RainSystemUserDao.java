package com.rao.dao.user;

import com.rao.mapper.RainBaseDao;
import com.rao.pojo.entity.user.RainSystemUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * DAO - RainSystemUser(系统用户表)
 * 
 * @author raojing
 * @version 2.0
 */
public interface RainSystemUserDao extends RainBaseDao<RainSystemUser> {

    /**
     * 通过用户名或手机号码查询用户信息
     * @param account
     * @return
     */
    RainSystemUser findByUserNameOrPhone(@Param("account") String account);

    /**
     * 通过用户名和密码查询记录数
     * @param userName
     * @param phone
     * @param id
     * @return
     */
    Integer countByUserNameOrPhone(@Param("userName") String userName, @Param("phone") String phone, @Param("id") Long id);

}
