package com.rao.service;

import com.rao.pojo.vo.UserLoginLogoutLogVO;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;

/**
 * @author : hudelin
 * @className :UserLogService
 * @description : 用户日志服务类接口
 * @date: 2020-01-13 14:45
 */
public interface UserLogService {

    /**
     * 用户登录登出日志分页
     * @param pageParam
     * @param userId
     * @param accountType
     * @return
     */
    PageResult<UserLoginLogoutLogVO> getLoginLogout(PageParam pageParam, Long userId, String accountType);
}
