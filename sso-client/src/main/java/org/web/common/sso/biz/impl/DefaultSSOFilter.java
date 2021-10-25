package org.web.common.sso.biz.impl;

import lombok.Getter;
import lombok.Setter;
import org.web.base.domain.ResultDO;
import org.web.common.sso.domain.SessionAccountDO;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;

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
