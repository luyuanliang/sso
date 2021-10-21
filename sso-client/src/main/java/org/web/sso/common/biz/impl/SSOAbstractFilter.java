package org.web.sso.common.biz.impl;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web.base.domain.ResultDO;
import org.web.base.helper.HttpRequestHelper;
import org.web.base.helper.PropertiesHelper;
import org.web.base.helper.StringHelper;
import org.web.sso.common.domain.SessionAccountDO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

@Setter
@Getter
public abstract class SSOAbstractFilter implements Filter, ConstantInterface {

    Logger logger = LoggerFactory.getLogger(SSOAbstractFilter.class);

    private SSOAbstractFilterConfig config = null;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletResponse;
        HttpServletResponse response;
        response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();


        // 第一个场景，从cookie或者request中获取token，如果token为空。
        String token = HttpRequestHelper.getValueByParamOrCookie(request, config.getParamToken());
        if (StringUtils.isEmpty(token)) {
            // 判断是否可是过滤免登陆URI，忽略大小写。
            String ignoreUri = config.getIgnoreURIList().stream().filter(str -> StringUtils.startsWithIgnoreCase(uri, str)).findAny().orElse(null);
            if (StringUtils.isNoneBlank(ignoreUri)) {
                filterChain.doFilter(request, response);
                return;
            }
            sendRedirect(response);
            return;
        }

        // 第二个场景，token不为空，交换账户信息。
        ResultDO<SessionAccountDO> getSessionByTokenResultDO = getSessionByToken(token, request);
        if (!getSessionByTokenResultDO.isSuccess()) {
            // 通过token信息失败，则需要重定向，需要重新登录。
            sendRedirect(response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        config = new SSOAbstractFilterConfig();
        SSOAbstractFilterConfig.init(config);
        logger.info(new Gson().toJson(config));

    }

    abstract ResultDO<SessionAccountDO> getSessionByToken(String token, HttpServletRequest request);

    protected void sendRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect(config.getRedirectURL());
    }
}


@Setter
@Getter
class SSOAbstractFilterConfig implements ConstantInterface {
    // 免登陆URI信息，分号分隔，并用于生成ignoreURIList;
    private String ignoreURIStr;
    // generate by ignoreURIStr when init method executing.
    private List<String> ignoreURIList;
    // token 的 参数名称
    private String paramToken = "token";
    private String redirectURL = null;

    public static void init(SSOAbstractFilterConfig config) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        // token 的默认名称是ssoToken，可以不进行配置。
        config.setParamToken(PropertiesHelper.getString(resourceBundle, PROPERTIES_TOKEN_NAME, DEFAULT_TOKEN_NAME));
        // 初始化重定向URL
        String redirectURL = PropertiesHelper.getString(resourceBundle, PROPERTIES_REDIRECT_URL, null);
        if (StringUtils.isEmpty(redirectURL)) {
            throw new RuntimeException(PROPERTIES_REDIRECT_URL + " 未有配置，必须制定重定向页面。");
        } else {
            config.setRedirectURL(redirectURL);
        }

        // 初始化免登陆URLS。
        String ignoreURLS = PropertiesHelper.getString(resourceBundle, PROPERTIES_IGNORE_LIST, null);
        if (StringUtils.isNoneBlank(ignoreURLS)) {
            config.setIgnoreURIStr(ignoreURLS);
            config.setIgnoreURIList(StringHelper.transStr2List(ignoreURLS, ";", String.class));
        }
    }
}