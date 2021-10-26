package org.web.common.sso.server.controller;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web.base.domain.ResultDO;
import org.web.base.domain.exception.ResultMessageEnum;

import org.web.base.domain.exception.ServiceException;
import org.web.base.domain.helper.ResultHelper;
import org.web.base.domain.helper.ServiceExceptionHelper;
import org.web.common.sso.domain.SessionAccountDO;
import org.web.common.sso.server.domain.SsoTokenDO;
import org.web.common.sso.server.helper.SSOServerConstant;
import org.web.common.sso.server.query.QuerySsoToken;
import org.web.common.sso.server.service.SsoTokenService;


import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("sso")
public class SsoController implements SSOServerConstant {

    Logger logger = LoggerFactory.getLogger(SsoController.ERROR_PARAM_LAST_ACCESS_TIME);

    @Resource
    private SsoTokenService ssoTokenService;


    /***
     *
     * */
    @RequestMapping(value = "getSessionDOByToken", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getSessionDOByToken(String token) {
        ResultDO<SessionAccountDO> resultDO = new ResultDO<>();
        Map<String, Object> param = new HashMap<>();
        param.put(ERROR_PARAM_TOKEN, token);
        try {
            if (StringUtils.isEmpty(token)) {
                throw ServiceExceptionHelper.buildServiceException(ResultMessageEnum.PARAM_EMPTY);
            }
            if (ServerParamsContainer.hasRedis) {
                System.out.println("hasRedis");
            } else {
                QuerySsoToken querySsoToken = new QuerySsoToken();
                querySsoToken.setSsoToken(token);
                SsoTokenDO ssoTokenDO = ssoTokenService.selectOneSsoToken(querySsoToken);
                // 判断是否存在
                if (ssoTokenDO == null) {
                    throw ServiceExceptionHelper.buildServiceException(ResultMessageEnum.DATA_NOT_EXIST, param);
                } else if (new Date().getTime() - ssoTokenDO.getUpdateTime().getTime() > ssoTokenDO.getExpireTime() * 1000) {
                    // 依据时间判断是否失效
                    param.put(ERROR_PARAM_LAST_ACCESS_TIME, ssoTokenDO.getExpireTime());
                    throw ServiceExceptionHelper.buildServiceException(ResultMessageEnum.DATA_LOSE_EFFICACY, param);
                }
                SessionAccountDO sessionAccountDO = new SessionAccountDO();
                sessionAccountDO.setAccountName(ssoTokenDO.getAccountName());
                sessionAccountDO.setAccountNum(ssoTokenDO.getAccountNum());
                sessionAccountDO.setSsoToken(token);
                resultDO.setDomain(sessionAccountDO);
            }
        } catch (ServiceException e) {
            resultDO = ResultHelper.buildResultDOByException(e);
        }
        return new Gson().toJson(resultDO);
    }

}
