package com.rao.service.impl;

import com.rao.cache.key.UserCacheKey;
import com.rao.client.MemberWalletClient;
import com.rao.constant.common.DateFormatEnum;
import com.rao.constant.common.StateConstants;
import com.rao.constant.sms.SmsOperationTypeEnum;
import com.rao.constant.user.UserCommonConstant;
import com.rao.constant.user.UserTypeEnum;
import com.rao.dao.RainMemberDao;
import com.rao.dao.RainSystemUserDao;
import com.rao.exception.BusinessException;
import com.rao.exception.DefaultSuccessMsgEnum;
import com.rao.pojo.bo.LoginUserBO;
import com.rao.pojo.dto.SmsSendDTO;
import com.rao.pojo.entity.RainMember;
import com.rao.pojo.entity.RainSystemUser;
import com.rao.service.UserService;
import com.rao.util.cache.RedisTemplateUtils;
import com.rao.util.common.CacheConstant;
import com.rao.util.common.RandomUtils;
import com.rao.util.common.TwiterIdUtil;
import com.rao.util.result.ResultMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户 service 实现
 * @author raojing
 * @date 2019/12/7 23:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RainSystemUserDao rainSystemUserDao;
    @Resource
    private RainMemberDao rainMemberDao;
    @Resource
    private RedisTemplateUtils redisTemplateUtils;
    @Resource
    private MemberWalletClient memberWalletClient;

    @Override
    public LoginUserBO findByAccountAndUserType(String userName, String type) {
        LoginUserBO loginUser = null;
        if(UserTypeEnum.ADMIN.getValue().equals(type)){
            // 系统管理员用户
            RainSystemUser systemUser = rainSystemUserDao.findByUserNameOrPhone(userName);
            if(systemUser != null){
                loginUser = new LoginUserBO();
                BeanUtils.copyProperties(systemUser, loginUser);
            }
        }else if(UserTypeEnum.C_USER.getValue().equals(type)){
            // C 端用户
            RainMember member = rainMemberDao.findByUserNameOrPhoneOrOpenId(userName);
            if(member != null){
                loginUser = new LoginUserBO();
                BeanUtils.copyProperties(member, loginUser);
                loginUser.setNickName(member.getNickname());
            }
        }
        return loginUser;
    }

    @Override
    public LoginUserBO findByOpenIdAndUserType(String openId, String accountType) {
        if(UserTypeEnum.C_USER.getValue().equals(accountType)){
            RainMember member = rainMemberDao.findByWxOpenId(openId);
            if(member != null){
                LoginUserBO loginUser = new LoginUserBO();
                BeanUtils.copyProperties(member, loginUser);
                loginUser.setNickName(member.getNickname());
                return loginUser;
            }
        }else{
            throw BusinessException.operate("用户未对接第三方登录");
        }
        return null;
    }

    @Override
    public RainMember registerMember(String wxOpenId) {
        /**
         * 调用支付系统初始化用户钱包
         */
        Long memberId = TwiterIdUtil.getTwiterId();
        // 设置用户注册缓存，有效时间为 1 分钟
        String userRegisterCacheKey = UserCacheKey.userRegisterCacheKey(memberId);
        redisTemplateUtils.set(userRegisterCacheKey, String.valueOf(memberId), CacheConstant.TIME_ONE);
        // 调用支付系统初始化用户钱包
        ResultMessage resultMessage = memberWalletClient.init(memberId);
        if(!resultMessage.getCode().equals(DefaultSuccessMsgEnum.SUCCESS.getCode())){
            throw BusinessException.operate("系统故障，登录失败");
        }

        /**
         * 注册用户信息
         */
        String userName = RandomUtils.randomStringWithTime(6, DateFormatEnum.FORMAT_CONNECT_EXTEND.getFormatString());
        Date now = new Date();
        RainMember rainMember = new RainMember();
        rainMember.setId(memberId);
        rainMember.setUserName(userName);
        rainMember.setPhone("");
        // 初始密码为 "" 的加密串
        rainMember.setPassword(UserCommonConstant.DEFAULT_PWD);
        rainMember.setNickname(userName);
        rainMember.setWxOpenid(wxOpenId);
        rainMember.setWxNickname("");
        rainMember.setEmail("");
        rainMember.setAvatar("");
        rainMember.setGender(1);
        rainMember.setStatus(StateConstants.STATE_ENABLE);
        rainMember.setPersonalSignature("");
        rainMember.setBirthday(now);
        rainMember.setCreateTime(now);
        rainMember.setUpdateTime(now);
        rainMemberDao.insert(rainMember);
        return rainMember;
    }

    @Override
    public void checkAccount(SmsSendDTO smsSendDTO) {
        SmsOperationTypeEnum operationType = SmsOperationTypeEnum.ofType(smsSendDTO.getType());
        UserTypeEnum userType = UserTypeEnum.ofValue(smsSendDTO.getAccountType());
        LoginUserBO userBO = this.findByAccountAndUserType(smsSendDTO.getPhone(), userType.getValue());
        switch (operationType){
            case LOGIN:
                // 登录
            case RESET_PWD:
                // 找回密码
            case VERIFY_IDENTITY:
                // 验证身份
                if(userBO == null){
                    throw BusinessException.operate("用户不存在哦");
                }
                if(!userBO.getStatus().equals(StateConstants.STATE_ENABLE)){
                    throw BusinessException.operate("账户不可用");
                }
                break;
            case REGISTER:
                // 注册
                if(userBO != null){
                    throw BusinessException.operate("用户已注册");
                }
                break;
            default:
                throw BusinessException.operate("检查账号失败");
        }
    }

}
