package com.ryums.securityexample.controller;

import com.ryums.securityexample.domain.UserMember;
import com.ryums.securityexample.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(value = "/main")
    public ModelAndView index(ModelMap modelMap, @AuthenticationPrincipal UserMember userMember) {
        modelMap.put("userMember", userMember);
        return new ModelAndView("/index", modelMap);
    }
}
