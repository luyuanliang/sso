/**
 * @Description SsoTokenService is generated by the auto build Tools.
 * @author luyl
 * @time 2021-10-25 21:07:06
 */

package org.web.common.sso.server.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import org.web.common.sso.server.query.QuerySsoToken;
import org.web.common.sso.server.domain.SsoTokenDO;
import org.web.common.sso.server.dao.SsoTokenDAO;
import org.web.base.domain.exception.ServiceException;

@Service("ssoTokenService")
public class SsoTokenService {

	private static Logger logger = Logger.getLogger(SsoTokenService.class);

	@Resource
	private SsoTokenDAO ssoTokenDAO;

	/**
	 * @Decription ����������ѯ��¼
	 * @param ssoTokenId
	 * @author luyl
	 * @date 2021-10-25 21:07:06
	 * @return ����Ψһ��¼SsoToken.
	 */
	public SsoTokenDO selectSsoTokenBySsoTokenId ( Long ssoTokenId) throws ServiceException {
		if(ssoTokenId == null){
			throw new ServiceException("PARAM_IS_EMPTY","Query is null.");
		}
		return ssoTokenDAO.selectSsoTokenBySsoTokenId(ssoTokenId);
	}

	/**
	 * @Decription ���ݲ�ѯ����,����List.
	 * @param querySsoToken ��װ�˲�ѯ��������.
	 * @author luyl
	 * @date 2021-10-25 21:07:06
	 * @return ����һ���¼.
	 */
	public List< SsoTokenDO > selectSsoTokenList(QuerySsoToken querySsoToken) throws ServiceException {

		if (querySsoToken == null) {
			throw new ServiceException("PARAM_IS_EMPTY","Query is null.");
		}
		//TODO
		setDefaultQuery(querySsoToken);
		return ssoTokenDAO.selectSsoTokenList( querySsoToken );
	}

	/**
	 * @Decription ���ݲ�ѯ����,����map,map�е�KEY�Ǳ�������ssoTokenId.
	 * @param querySsoToken ��װ�˲�ѯ��������.
	 * @author luyl
	 * @date 2021-10-25 21:07:06
	 * @return ����һ���¼����������ΪKEY��MAP.
	 */
	public Map< Long, SsoTokenDO > selectSsoTokenMapForSsoTokenId(QuerySsoToken querySsoToken) throws ServiceException {

		if (querySsoToken == null) {
			throw new ServiceException("PARAM_IS_EMPTY","Query is null.");
		}
		//TODO
		setDefaultQuery(querySsoToken);
		List< SsoTokenDO >  list = ssoTokenDAO.selectSsoTokenList( querySsoToken );
		Map<  Long, SsoTokenDO > map = new HashMap<>();
		for ( SsoTokenDO ssoTokenDO : list) {
			map.put(ssoTokenDO.getSsoTokenId(), ssoTokenDO);
		}
		return map;
	}


	/**
	 * @Decription ���ݲ�ѯ����,��ѯ���������ļ�¼��.
	 * @param querySsoToken ��װ�˲�ѯ��������.
	 * @author luyl
	 * @date 2021-10-25 21:07:06
	 * @return ���ز�ѯ�������صļ�¼����.
	 */
	public Integer countSsoTokenList(QuerySsoToken querySsoToken) throws ServiceException {
		if (querySsoToken == null) {
			throw new ServiceException("PARAM_IS_EMPTY","Query is null.");
		}
		setDefaultQuery(querySsoToken);
		return ssoTokenDAO.countSsoTokenList( querySsoToken );
	}

	/**
	 * Ĭ�ϲ���ѯ�߼�ɾ��������
	 */
	private void setDefaultQuery(QuerySsoToken querySsoToken){
		if (StringUtils.isEmpty(querySsoToken.getIsDelete())) {
			querySsoToken.setIsDelete("NO");
		}
		if (StringUtils.isEmpty(querySsoToken.getOrderByClause())) {
			//querySsoToken.setOrderByClause("  updateDate DESC ");
		}
	}

	/**
	 * @Decription ���ݲ�ѯ����,���ص�һ����¼.
	 * @param querySsoToken ��װ�˲�ѯ��������.
	 * @author luyl
	 * @date 2021-10-25 21:07:06
	 * @return ���ص�һ����¼.
	 */
	public SsoTokenDO selectOneSsoToken (QuerySsoToken querySsoToken) throws ServiceException {
		querySsoToken.setFirstRecord();
		List<SsoTokenDO> list = ssoTokenDAO.selectSsoTokenList( querySsoToken );
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * @Decription ���ݲ�ѯ����,��ѯ���ظ���Ϣ.
	 * @param querySsoToken ��װ�˲�ѯ��������.
	 * @author luyl
	 * @date 2021-10-25 21:07:06
	 * @return ���ز��ظ���Ϣ.
	 */
	public List<String> selectDistinctList(QuerySsoToken querySsoToken)throws ServiceException {
		if(StringUtils.isEmpty(querySsoToken.getIsDelete())){
			querySsoToken.setIsDelete("NO");
		}
		return ssoTokenDAO.selectDistinctList(querySsoToken);
	}


	/**
	 * @Decription ����һ���¼�¼.
	 * @param ssoTokenDO ��װ�����Ķ���.
	 * @author luyl
	 * @date 2021-10-25 21:07:06
	 * @return ����ԭʼ��������õ����ݿ����������������Զ�������������.
	 */
	public SsoTokenDO insertSsoToken(SsoTokenDO ssoTokenDO)throws ServiceException {
		// check params first.
		checkInsert(ssoTokenDO);

		// TODO add default value.

		ssoTokenDAO.insertSsoToken(ssoTokenDO);
		return ssoTokenDO;
	}

	/**
	 * @Decription ���������޸ļ�¼.
	 * @param ssoTokenDO ��װ�޸ĵĶ���.
	 * @author luyl
	 * @date 2021-10-25 21:07:06
	 * @return �����޸ļ�¼��.
	 */
	public int updateSsoTokenBySsoTokenId(SsoTokenDO ssoTokenDO)throws ServiceException {
		// check params first.
		checkUpdate(ssoTokenDO);

		return ssoTokenDAO.updateSsoTokenBySsoTokenId(ssoTokenDO);

	}

	/**
	 * According to DB info. check attribute allow empty or not, and check attribute's length is over upper limit of length or not.
	 * and this method is generate by the auto build tool.
	 */
	@SuppressWarnings({"deprecation" })
	private void checkInsert(SsoTokenDO ssoTokenDO) throws ServiceException {
		if( ssoTokenDO == null){
			throw new ServiceException("PARAM_IS_EMPTY","Query is null.");
		}
		else if (ssoTokenDO.getSsoTokenId()==null
				|| (ssoTokenDO.getSsoTokenId()!=null && String.valueOf(ssoTokenDO.getSsoTokenId()).length() > 10)) {
			throw new ServiceException("PARAM_IS_INVALID","SsoTokenId is null or out of range, Upper limit of length is 10");
		}
		else if (StringUtils.isEmpty(ssoTokenDO.getSsoToken())
				|| (StringUtils.isNotEmpty(ssoTokenDO.getSsoToken()) && ssoTokenDO.getSsoToken().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID","SsoToken is null or out of range, Upper limit of length is 255");
		}
		else if (StringUtils.isEmpty(ssoTokenDO.getAccountNum())
				|| (StringUtils.isNotEmpty(ssoTokenDO.getAccountNum()) && ssoTokenDO.getAccountNum().length() > 11)) {
			throw new ServiceException("PARAM_IS_INVALID","AccountNum is null or out of range, Upper limit of length is 11");
		}
		else if (StringUtils.isEmpty(ssoTokenDO.getAccountName())
				|| (StringUtils.isNotEmpty(ssoTokenDO.getAccountName()) && ssoTokenDO.getAccountName().length() > 11)) {
			throw new ServiceException("PARAM_IS_INVALID","AccountName is null or out of range, Upper limit of length is 11");
		}
		else if (ssoTokenDO.getExpireTime()==null
				|| (ssoTokenDO.getExpireTime()!=null && String.valueOf(ssoTokenDO.getExpireTime()).length() > 10)) {
			throw new ServiceException("PARAM_IS_INVALID","ExpireTime is null or out of range, Upper limit of length is 10");
		}
		else if (StringUtils.isEmpty(ssoTokenDO.getIsDelete())
				|| (StringUtils.isNotEmpty(ssoTokenDO.getIsDelete()) && ssoTokenDO.getIsDelete().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID","IsDelete is null or out of range, Upper limit of length is 255");
		}

	}

	@SuppressWarnings({"deprecation" })
	private void checkUpdate(SsoTokenDO ssoTokenDO) throws ServiceException {
		if( ssoTokenDO == null){
			throw new ServiceException("PARAM_IS_EMPTY","Query is null.");
		}
		else if (ssoTokenDO.getSsoTokenId()!=null && String.valueOf(ssoTokenDO.getSsoTokenId()).length() > 10) {
			throw new ServiceException("UPDATE_ERROR","SsoTokenId is out of range, Upper limit of length is 10");
		}
		else if (StringUtils.isNotEmpty(ssoTokenDO.getSsoToken()) && ssoTokenDO.getSsoToken().length() > 255) {
			throw new ServiceException("UPDATE_ERROR","SsoToken is out of range, Upper limit of length is 255");
		}
		else if (StringUtils.isNotEmpty(ssoTokenDO.getAccountNum()) && ssoTokenDO.getAccountNum().length() > 11) {
			throw new ServiceException("UPDATE_ERROR","AccountNum is out of range, Upper limit of length is 11");
		}
		else if (StringUtils.isNotEmpty(ssoTokenDO.getAccountName()) && ssoTokenDO.getAccountName().length() > 11) {
			throw new ServiceException("UPDATE_ERROR","AccountName is out of range, Upper limit of length is 11");
		}
		else if (ssoTokenDO.getExpireTime()!=null && String.valueOf(ssoTokenDO.getExpireTime()).length() > 10) {
			throw new ServiceException("UPDATE_ERROR","ExpireTime is out of range, Upper limit of length is 10");
		}
		else if (StringUtils.isNotEmpty(ssoTokenDO.getIsDelete()) && ssoTokenDO.getIsDelete().length() > 255) {
			throw new ServiceException("UPDATE_ERROR","IsDelete is out of range, Upper limit of length is 255");
		}
	}
}


