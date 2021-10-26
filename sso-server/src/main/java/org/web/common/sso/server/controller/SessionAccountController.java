package org.web.common.sso.server.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web.base.domain.ResultDO;
import org.web.common.sso.domain.SessionAccountDO;

@Controller
@RequestMapping("sessionAccount")
public class SessionAccountController {

    Logger logger = LoggerFactory.getLogger(SessionAccountController.class);

    @RequestMapping(value = "signIn", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String signIn(String token) {
        ResultDO<SessionAccountDO> resultDO = new ResultDO<>();
        return new Gson().toJson(resultDO);
    }

}
