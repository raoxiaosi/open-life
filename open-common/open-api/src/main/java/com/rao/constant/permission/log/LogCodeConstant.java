package com.rao.constant.permission.log;

import com.rao.annotation.PermissionDesc;

/**
 * 日志服务-log-权限标识
 * @author raojing
 * @date 2019/12/17 20:17
 */
public interface LogCodeConstant {

    /********************************* 日志服务相关 *********************************/

    /**
     * 日志登录登出模块组
     */
    @PermissionDesc(desc = "")
    String LOG_LOGINLOGOUT_GROUP = "log:loginLogout:group";

    /**
     * 用户登录登出日志列表
     */
    @PermissionDesc(desc = "用户登录登出日志")
    String LOG_LOGINLOGOUT_USER_LIST = "log:loginLogout:user:list";


    /********************************* 日志服务相关 *********************************/
}
