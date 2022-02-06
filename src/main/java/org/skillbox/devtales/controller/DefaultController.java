package org.skillbox.devtales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String redirectToIndex() {
        return "index";
    }

    @RequestMapping(method = {RequestMethod.OPTIONS, RequestMethod.GET},
            value = "/**/{path:[^.]*}")
    public String redirectToForward() {
        return "forward:/";
    }

}