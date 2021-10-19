package org.web.sso.common.biz.impl;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.web.base.domain.ResultDO;
import org.web.sso.common.domain.SessionAccountDO;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Setter
@Getter
public class DefaultSSOFilter extends SSOAbstractFilter {

    private boolean hasRedisCache = false;


    @Override
    ResultDO<SessionAccountDO> getSessionByToken(String token, HttpServletRequest request) {
        ResultDO<SessionAccountDO> resultDO = new ResultDO<>();
        try {
            if (hasRedisCache) {

            }
        } catch (Exception e) {

        } finally {

        }

        return resultDO;
    }


    @Override
    public void init(FilterConfig filterConfig) {
        super.init(filterConfig);
    }

}
