package com.rao.controller.system;

import com.rao.annotation.BeanValid;
import com.rao.annotation.SimpleValid;
import com.rao.constant.permission.user.SystemCodeConstant;
import com.rao.pojo.dto.SaveRoleDTO;
import com.rao.pojo.vo.system.ListRoleVO;
import com.rao.pojo.vo.system.PageRoleVO;
import com.rao.pojo.vo.system.RoleDetailVO;
import com.rao.service.system.RoleService;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import com.rao.util.result.ResultMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色相关
 * @author raojing
 * @date 2019/12/8 14:06
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 新增角色
     * @param roleDTO
     * @return
     */
    @PostMapping()
    @PreAuthorize("hasAuthority('" + SystemCodeConstant.ADMIN_ROLE_ADD + "')")
    public ResultMessage saveRole(@BeanValid @RequestBody SaveRoleDTO roleDTO){
        roleService.saveRole(roleDTO);
        return ResultMessage.success().message("保存角色成功");
    }

    /**
     * 角色列表
     * @return
     */
    @GetMapping()
    @PreAuthorize("hasAuthority('" + SystemCodeConstant.ADMIN_ROLE_LIST + "')")
    public ResultMessage<PageResult<PageRoleVO>> pageRole(PageParam pageParam){
        PageResult<PageRoleVO> pageResult = roleService.pageRole(pageParam);
        return ResultMessage.success(pageResult);
    }

    /**
     * 角色详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + SystemCodeConstant.ADMIN_ROLE_DETAIL + "')")
    public ResultMessage<RoleDetailVO> findRole(@PathVariable("id") Long id) {
        RoleDetailVO roleDetailVO = roleService.findById(id);
        return ResultMessage.success(roleDetailVO);
    }

    /**
     * 修改角色
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + SystemCodeConstant.ADMIN_ROLE_UPDATE + "')")
    public ResultMessage findRole(@PathVariable("id") Long id, @BeanValid @RequestBody SaveRoleDTO roleDTO) {
        roleService.updateRole(id, roleDTO);
        return ResultMessage.success();
    }

    /**
     * 删除单个角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + SystemCodeConstant.ADMIN_ROLE_DELETE + "')")
    public ResultMessage delete(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return ResultMessage.success("删除角色成功");
    }

    /**
     * 角色列表（全部-新增用户展示）
     * @return
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('" + SystemCodeConstant.ADMIN_ROLE_LIST_ALL + "')")
    public ResultMessage<List<ListRoleVO>> listRole(){
        List<ListRoleVO> roleDetailVOList = roleService.listRole();
        return ResultMessage.success(roleDetailVOList);
    }

    /**
     * 删除用户角色
     * @param roleId
     * @param userId
     * @return
     */
    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasAuthority('" + SystemCodeConstant.ADMIN_ROLE_DELETE_USER + "')")
    public ResultMessage deleteUserRole(@PathVariable("roleId") Long roleId,
                                        @SimpleValid @NotNull(message = "用户id不能为空") @RequestParam Long userId){
        roleService.deleteUserRole(userId, roleId);
        return ResultMessage.success();
    }

}
