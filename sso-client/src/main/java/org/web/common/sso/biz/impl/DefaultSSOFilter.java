package org.web.common.sso.biz.impl;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web.base.domain.ResultDO;
import org.web.base.domain.exception.ResultMessageEnum;
import org.web.base.domain.exception.ServiceException;
import org.web.base.domain.helper.ResultHelper;
import org.web.base.domain.helper.ServiceExceptionHelper;
import org.web.base.helper.HttpHelper;
import org.web.base.helper.PropertiesHelper;
import org.web.common.sso.domain.SessionAccountDO;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

@Setter
@Getter
public class DefaultSSOFilter extends SSOAbstractFilter {

    Logger logger = LoggerFactory.getLogger(DefaultSSOFilter.class);

    private String getAccountInfoByTokenURL = null;

    @Override
    ResultDO<SessionAccountDO> getSessionByToken(String token, HttpServletRequest request) {
        ResultDO<SessionAccountDO> resultDO = new ResultDO<>();
        try {
            String str = HttpHelper.requestByGet(getAccountInfoByTokenURL + "?ssoToken=" + token);
            Gson gson = new Gson();
            resultDO = gson.fromJson(str, ResultDO.class);
        } catch (Exception e) {
            logger.error("Execute token is " + token);
            logger.error(ServiceExceptionHelper.getExceptionInfo(e));
            resultDO = ResultHelper.buildResultDOByException(e);
        }
        return resultDO;
    }


    @Override
    public void init(FilterConfig filterConfig) {
        super.init(filterConfig);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        getAccountInfoByTokenURL = PropertiesHelper.getString(resourceBundle, PROPERTIES_SSO_URL, null);
        if (StringUtils.isEmpty(getAccountInfoByTokenURL)) {
            throw ServiceExceptionHelper.buildServiceException(ResultMessageEnum.INIT_PARAM_NOT_SETTED, PROPERTIES_SSO_URL + "没有设置");
        }
    }

}
