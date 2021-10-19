package org.web.sso.common.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionAccountDO {

    private String ssoToken;
    private String accountNum;
    private String accountName;
}
