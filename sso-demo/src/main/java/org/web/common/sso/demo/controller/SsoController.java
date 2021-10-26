package org.web.common.sso.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("helloDemo")
class HelloDemoController {

    /***
     *
     * */
    @RequestMapping(value = "getFirstWorld", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getFirstWorld(String ssoToken) {
        return "welcome first sso app.";
    }


}
