package com.rao.util.common;

import com.rao.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author raojing
 * @date 2019/12/20 16:20
 */
@Slf4j
public class PermissionClazzUtil {
    
    private static List<Class> clazzList = new ArrayList<>();
    
    static {
        try {
            Set<String> packageClass = PackageScanUtil.findPackageClass("com.rao.constant.permission");
            for (String aClass : packageClass) {
                clazzList.add(Class.forName(aClass));
            }
        } catch (Exception e) {
            log.error("加载类异常--->{}", e.getMessage());
            throw BusinessException.operate("加载类异常");
        }        
    }
    
    public static List<Class> allPermissionClazz(){
        return clazzList;
    }
    
}
