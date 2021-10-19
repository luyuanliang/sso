package org.web.sso.common.server.query;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.web.base.domain.QueryBase;

@Setter
@Getter
public class QuerySsoToken extends QueryBase {


    private Long ssoTokenId;

    private List<Long> ssoTokenIdList;

    private String ssoToken;

    private String accountNum;

    private String accountName;

    private Long expireTime;

    private Long moreThanExpireTime;
    private Long lessThanExpireTime;
    private Long equalAndMoreThanExpireTime;
    private Long equalAndLessThanExpireTime;

    private Date updateTime;

    private Date moreThanUpdateTime;
    private Date lessThanUpdateTime;
    private Date equalAndMoreThanUpdateTime;
    private Date equalAndLessThanUpdateTime;

    private Date createTime;

    private Date moreThanCreateTime;
    private Date lessThanCreateTime;
    private Date equalAndMoreThanCreateTime;
    private Date equalAndLessThanCreateTime;

    private String isDelete;
}

