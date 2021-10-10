package org.web.sso.common.domain;

import org.web.base.domain.ResultDO;

public interface SSOBiz {

    default ResultDO<String> getResultDO() {
        return null;
    }


}
