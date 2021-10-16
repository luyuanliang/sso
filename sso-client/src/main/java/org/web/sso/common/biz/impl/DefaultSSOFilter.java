package org.web.sso.common.biz.impl;

import lombok.Getter;
import lombok.Setter;
import org.web.domain.ResultDO;
import org.web.sso.common.domain.SessionAccountDO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Setter
@Getter
public class DefaultSSOFilter extends SSOAbstractFilter {

    // 免登陆URI信息，分号分隔，并用于生成ignoreURIList;
    private String ignoreURIStr;
    // generate by ignoreURIStr when init method executing.
    private ArrayList<String> ignoreURIList;

    @Override
    ResultDO<SessionAccountDO> getSessionByToken(String token, HttpServletRequest request) {
        return null;
    }

}
