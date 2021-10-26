package org.web.common.sso.biz.impl;

import com.google.gson.Gson;
import org.web.base.domain.ResultDO;
import org.web.common.sso.domain.SessionAccountDO;

public class A {
    public static void main(String[] args) {
        ResultDO<SessionAccountDO> resultDO = new ResultDO<>();
        try {
            Gson gson = new Gson();
            String str ="{\"success\":true,\"totalCount\":1,\"domain\":{\"ssoToken\":\"firsttoken\",\"accountNum\":\"25998\",\"accountName\":\"luyl\"}}";
            resultDO = gson.fromJson(str,ResultDO.class);
            System.out.println();
        } catch (Exception e) {

        } finally {

        }
    }
}
