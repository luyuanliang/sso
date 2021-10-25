package org.web.common.sso.server.controller;

import org.web.base.helper.PropertiesHelper;
import org.web.common.sso.server.helper.SSOServerConstant;

import java.util.ResourceBundle;

public class ServerParamsContainer implements SSOServerConstant {

    // 是否有缓存进行提速。
    public static boolean hasRedis = false;
    public static boolean needUpdateEveryTime = false;


    // init param
    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        hasRedis = PropertiesHelper.getBoolean(resourceBundle, PROPERTIES_HAS_REDIS, false);
        needUpdateEveryTime = PropertiesHelper.getBoolean(resourceBundle, PROPERTIES_NEED_UPDATE_EVERY_TIME, false);
    }
}
