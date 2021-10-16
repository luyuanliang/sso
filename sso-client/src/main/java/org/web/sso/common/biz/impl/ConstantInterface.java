package org.web.sso.common.biz.impl;

public interface ConstantInterface {

    String PARAM_TOKEN = "SSO_TOKEN";
    String PARAM_USER_NAME = "userName";
    String PARAM_PASSWORD = "password";

    /***
     * PROPERTIES_ 配置文件的参数名称。
     */
    String PROPERTIES_TOKEN_NAME = "ORG.WEB.SSO.TOKEN"; // token 在cookie中的定义变量名称
    String PROPERTIES_IGNORE_LIST = "ORG.WEB.SSO.IGNORE"; // 免登陆URI信息，可以为空，用分号分隔。
    String PROPERTIES_USER_NAME = "ORG.WEB.SSO.USERNAME";
    String PROPERTIES_PASSWORD = "ORG.WEB.SSO.PASSWORD";
    String PROPERTIES_REDIRECT_URL = "ORG.WEB.SSO.REDIRECT.URL"; // 重定向URL，可以是三方URL。

    String DEFAULT_TOKEN_NAME = "ssoToken";
    String DEFAULT_USER_NAME = "ssoUserName";
    String DEFAULT_PASSWORD = "ssoPassword";

    String COOKIE_TOKEN = "ssoToken";


}
