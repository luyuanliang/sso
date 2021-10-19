package org.web.sso.common.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDAO {

	public AccountDO selectAccountByAccountId(Long accountId);

	public List< AccountDO > selectAccountList(QueryAccount queryAccount);

	public Integer countAccountList(QueryAccount queryAccount);

	public int insertAccount(AccountDO accountDO);

	public int updateAccountByAccountId(AccountDO accountDO);
	
	public List<String> selectDistinctList(QueryAccount queryAccount);

}

