package com.rao.dao.system;

import com.rao.mapper.RainBaseDao;
import com.rao.pojo.entity.system.RainRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DAO - RainRole(角色)
 *
 * @author raojing
 * @version 2.0
 */
public interface RainRoleDao extends RainBaseDao<RainRole> {

    /**
     * 通过角色id查询
     *
     * @param roleIds
     * @return
     */
    List<RainRole> findByRoleIdList(@Param("roleIds") List<Long> roleIds);

}
