package org.web.common.sso.domain;

import org.web.base.domain.ResultDO;

public interface SSOBiz {

    default ResultDO<String> getResultDO() {
        return null;
    }


}
