package org.web.sso.common.server.controller;

import org.web.base.helper.PropertiesHelper;
import org.web.sso.common.server.helper.SSOServerConstant;

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
