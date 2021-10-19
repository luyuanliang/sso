package org.web.sso.common.helper;

import io.micrometer.core.instrument.util.StringUtils;
import org.web.base.helper.HttpRequestHelper;
import org.web.sso.common.biz.impl.ConstantInterface;
import org.web.sso.common.domain.SessionAccountDO;

import javax.servlet.http.HttpServletRequest;

public class SessionAccountHelper implements ConstantInterface {

    public static SessionAccountDO getSessionAccountFromRequest(HttpServletRequest httpServletRequest) {
        String value = HttpRequestHelper.getValueByParamOrCookie(httpServletRequest, COOKIE_TOKEN);
        if (StringUtils.isNotBlank(value)) {
            SessionAccountDO sessionAccountDO = new SessionAccountDO();
            String[] array = value.split("@");
            sessionAccountDO.setAccountNum(array[0]);
            sessionAccountDO.setAccountName(array[1]);
            return sessionAccountDO;
            //return new Gson().fromJson(value, SessionAccountDO.class);
        }
        return null;
    }
}
