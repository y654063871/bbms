package com.augmentum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AjaxForwardController{

    @RequestMapping(value = "showAllAjax", method = RequestMethod.GET)
    public String toShowAllAjax() {
        return "question/question_management";
    }
}
