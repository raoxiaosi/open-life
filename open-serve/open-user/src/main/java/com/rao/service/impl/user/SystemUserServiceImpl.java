package com.rao.service.impl.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rao.constant.common.StateConstants;
import com.rao.dao.system.RainRoleDao;
import com.rao.dao.system.RainUserRoleDao;
import com.rao.dao.user.RainSystemUserDao;
import com.rao.exception.BusinessException;
import com.rao.pojo.dto.SaveSystemUserDTO;
import com.rao.pojo.entity.system.RainPermission;
import com.rao.pojo.entity.system.RainRole;
import com.rao.pojo.entity.system.RainUserRole;
import com.rao.pojo.entity.user.RainSystemUser;
import com.rao.pojo.vo.user.SystemUserDetailVO;
import com.rao.pojo.vo.user.SystemUserVO;
import com.rao.pojo.vo.user.UserRoleVO;
import com.rao.service.user.SystemUserService;
import com.rao.util.common.CopyUtil;
import com.rao.util.common.TwiterIdUtil;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName : SystemUserServiceImpl  //类名
 * @Description : 系统用户操作相关  //描述
 * @Author : xujianjie  //作者
 * @Date: 2019-12-16 16:43  //时间
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Resource
    private RainSystemUserDao rainSystemUserDao;
    @Resource
    private RainUserRoleDao rainUserRoleDao;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    private RainRoleDao rainRoleDao;

    @Override
    public PageResult<SystemUserVO> getSystemUserList(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize());
        List<RainSystemUser> systemUserList = rainSystemUserDao.selectAll();
        PageInfo pageInfo = PageInfo.of(systemUserList);
        // 封装视图模型
        List<SystemUserVO> systemUserVOList = CopyUtil.transToObjList(systemUserList, SystemUserVO.class);
        return PageResult.build(pageInfo.getTotal(), systemUserVOList);
    }

    @Override
    public SystemUserDetailVO findSystemUserById(Long id) {
        RainSystemUser rainSystemUser = rainSystemUserDao.selectByPrimaryKey(id);
        SystemUserDetailVO systemUserDetailVO = CopyUtil.transToObj(rainSystemUser, SystemUserDetailVO.class);
        systemUserDetailVO.setUserRoleVOList(userRoles(id));
        return systemUserDetailVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addSystemUser(SaveSystemUserDTO systemUserDTO) {
        //判断用户名是否已经存在
        String userName = systemUserDTO.getUserName();
        String phone = systemUserDTO.getPhone();
        int count = rainSystemUserDao.countByUserNameOrPhone(userName, phone, null);
        if (count > 0) {
            throw BusinessException.operate("用户名或手机号码已存在");
        }

        //保存系统用户信息
        Date now = new Date();
        Long userId = TwiterIdUtil.getTwiterId();
        RainSystemUser systemUser = new RainSystemUser();
        systemUser.setId(userId);
        systemUser.setUserName(userName);
        systemUser.setPhone(phone);
        systemUser.setPassword(bCryptPasswordEncoder.encode(systemUserDTO.getPassword()));
        systemUser.setNickName("");
        systemUser.setEmail("");
        systemUser.setAvatar("");
        systemUser.setStatus(StateConstants.STATE_ENABLE);
        systemUser.setCreateTime(now);
        systemUser.setUpdateTime(now);
        systemUser.setDeleteTime(now);
        rainSystemUserDao.insertSelective(systemUser);

        //保存用户角色关系
        List<RainUserRole> userRoleList = systemUserDTO.getRoleId().stream().map(item -> {
            return RainUserRole.builder()
                    .roleId(item)
                    .userId(userId)
                    .build();
        }).collect(Collectors.toList());
        rainUserRoleDao.insertList(userRoleList);
    }

    @Override
    public void updateSystemUser(Long id, SaveSystemUserDTO systemUserDTO) {
        // 查询用户信息
        RainSystemUser rainSystemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if (null == rainSystemUser) {
            throw BusinessException.operate("用户不存在");
        }
        // 修改用户信息，需要对用户名和手机号码进行校验
        String userName = systemUserDTO.getUserName();
        String phone = systemUserDTO.getPhone();
        int count = rainSystemUserDao.countByUserNameOrPhone(userName, phone, id);
        if(count > 0){
            throw BusinessException.operate("用户名或手机号码已存在");
        }
        // 保存用户信息
        RainSystemUser updateUser = new RainSystemUser();
        updateUser.setId(id);
        updateUser.setUserName(userName);
        updateUser.setPhone(phone);
        updateUser.setPassword(bCryptPasswordEncoder.encode(systemUserDTO.getPassword()));
        rainSystemUserDao.updateByPrimaryKeySelective(updateUser);

        //删除用户旧的角色信息
        Example userRoleExample = new Example(RainPermission.class);
        userRoleExample.createCriteria().andEqualTo("userId", id);
        rainUserRoleDao.deleteByExample(userRoleExample);

        //保存新的角色信息
        List<RainUserRole> userRoleList = systemUserDTO.getRoleId().stream().map(item -> {
            return RainUserRole.builder()
                    .roleId(item)
                    .userId(id)
                    .build();
        }).collect(Collectors.toList());
        rainUserRoleDao.insertList(userRoleList);
    }

    @Override
    public void deleteUserById(Long id) {
        RainSystemUser rainSystemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if (null == rainSystemUser) {
            throw BusinessException.operate(id + "不存在");
        }
        RainSystemUser updateUser=new RainSystemUser();
        updateUser.setId(id);
        updateUser.setStatus(StateConstants.STATE_DELETE);
        updateUser.setDeleteTime(new Date());
        rainSystemUserDao.updateByPrimaryKeySelective(updateUser);
    }

    @Override
    public void updateUserStatus(Long id, Integer status) {
        RainSystemUser rainSystemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if (null == rainSystemUser) {
            throw BusinessException.operate(id + "不存在");
        }

        //更新用户表的信息
        RainSystemUser updateUser=new RainSystemUser();
        updateUser.setId(id);
        updateUser.setStatus(status);
        updateUser.setUpdateTime(new Date());
        rainSystemUserDao.updateByPrimaryKeySelective(updateUser);
    }

    @Override
    public void resetPassword(Long id, String password) {
        RainSystemUser rainSystemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if (null == rainSystemUser) {
            throw BusinessException.operate(id + "不存在");
        }
        RainSystemUser updateUser=new RainSystemUser();
        updateUser.setId(rainSystemUser.getId());
        updateUser.setPassword(bCryptPasswordEncoder.encode(password));
        updateUser.setUpdateTime(new Date());
        rainSystemUserDao.updateByPrimaryKeySelective(updateUser);
    }

    @Override
    public List<UserRoleVO> userRoles(Long id) {
        //查询用户角色关联
        Example userRoleExample = new Example(RainUserRole.class);
        userRoleExample.createCriteria().andEqualTo("userId", id);
        List<RainUserRole> rainUserRoleList = rainUserRoleDao.selectByExample(userRoleExample);

        //查询角色
        if(!CollectionUtils.isEmpty(rainUserRoleList)){
            List<Long> roleIds = rainUserRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
            List<RainRole> rainRoleList = rainRoleDao.findByRoleIdList(roleIds);
            List<UserRoleVO> userRoleVOList = CopyUtil.transToObjList(rainRoleList, UserRoleVO.class);
            return userRoleVOList;
        }
        return new ArrayList<>(0);
    }
}
