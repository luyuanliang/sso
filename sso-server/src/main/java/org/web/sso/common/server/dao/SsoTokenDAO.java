package org.web.sso.common.server.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.web.sso.common.server.query.QuerySsoToken;
import org.web.sso.common.server.domain.SsoTokenDO;

@Mapper
public interface SsoTokenDAO {

	SsoTokenDO selectSsoTokenBySsoTokenId(Long ssoTokenId);

	List< SsoTokenDO > selectSsoTokenList(QuerySsoToken querySsoToken);

	Integer countSsoTokenList(QuerySsoToken querySsoToken);

	int insertSsoToken(SsoTokenDO ssoTokenDO);

	int updateSsoTokenBySsoTokenId(SsoTokenDO ssoTokenDO);

	List<String> selectDistinctList(QuerySsoToken querySsoToken);

}

