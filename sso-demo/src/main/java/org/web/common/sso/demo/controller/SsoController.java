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
    public String getFirstWorld(String token) {
        return token;
    }
    public static String str = null;

    static {
        System.out.println("1");
        str = "112233";
    }

    public static void main(String[] args) {
        System.out.println(str);
    }

}
