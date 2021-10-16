package org.web.sso.common.biz.impl;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web.domain.ResultDO;
import org.web.helper.HttpRequestHelper;
import org.web.sso.common.domain.SessionAccountDO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Setter
@Getter
public abstract class SSOAbstractFilter implements Filter, ConstantInterface {

    Logger logger = LoggerFactory.getLogger(SSOAbstractFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletResponse;
        HttpServletResponse response;
        response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();


        // 第一步，从cookie或者request中获取token。
        String token = HttpRequestHelper.getValueByParamOrCookie(request, getParamToken());
        if (StringUtils.isEmpty(token)) {
            // 判断是否可是过滤免登陆URI，忽略大小写。
            String ignoreUri = getIgnoreURIList().stream().filter(str -> StringUtils.startsWithIgnoreCase(uri, str)).findAny().orElse(null);
            if (StringUtils.isNoneBlank(ignoreUri)) {
                filterChain.doFilter(request, response);
                return;
            }
            response.sendRedirect(getRedirectURL());
            return;
        }

        ResultDO<SessionAccountDO> getSessionByTokenResultDO = getSessionByToken(token, request);
        if (!getSessionByTokenResultDO.isSuccess()) {
            response.sendRedirect(getRedirectURL());
            return;
        }

        // 基于token，交换获取session 信息。


    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    abstract ResultDO<SessionAccountDO> getSessionByToken(String token, HttpServletRequest request);

    // 免登陆URI信息，分号分隔，并用于生成ignoreURIList;
    private String ignoreURIStr;
    // generate by ignoreURIStr when init method executing.
    private ArrayList<String> ignoreURIList;

    private String paramToken = "token";
    private String redirectURL = null;

}
