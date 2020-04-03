package com.rao.controller;

import com.rao.annotation.SimpleValid;
import com.rao.constant.permission.log.LogCodeConstant;
import com.rao.pojo.vo.UserLoginLogoutLogVO;
import com.rao.service.UserLogService;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import com.rao.util.result.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author : hudelin
 * @className :UserLogController
 * @description :用户日志
 * @date: 2020-01-13 14:31
 */
@RestController
@RequestMapping("/log/login_logout")
public class UserLogController {

    @Autowired
    private UserLogService userLogService;

    /**
     * 用户列表
     *
     * @return
     */
    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('" + LogCodeConstant.LOG_LOGINLOGOUT_USER_LIST + "')")
    public ResultMessage<PageResult<UserLoginLogoutLogVO>> list(PageParam pageParam,
                                                                @PathVariable("userId") Long userId,
                                                                @SimpleValid @NotBlank(message = "账号类型不能为空") @RequestParam("accountType") String accountType) {
        PageResult<UserLoginLogoutLogVO> logoutLogVOPage = userLogService.getLoginLogout(pageParam, userId, accountType);
        return ResultMessage.success(logoutLogVOPage);
    }
}
