package com.rao.client.fallback;

import com.rao.client.MemberWalletClient;
import com.rao.util.result.ResultMessage;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 熔断器
 * @author raojing
 * @date 2020/1/9 10:47
 */
@Component
public class MemberWalletClientFallback implements FallbackFactory<MemberWalletClient> {
    @Override
    public MemberWalletClient create(Throwable throwable) {
        return new MemberWalletClient(){

            @PostMapping("/member/wallet/init")
            @Override
            public ResultMessage init(Long userId) {
                return ResultMessage.fail().message("初始化会员钱包失败");
            }
            
        };
    }
}
