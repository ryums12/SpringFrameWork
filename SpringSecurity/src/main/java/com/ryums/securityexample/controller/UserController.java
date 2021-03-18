package com.ryums.securityexample.controller;

import com.ryums.securityexample.dto.UserDTO;
import com.ryums.securityexample.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@AllArgsConstructor
@Controller
public class UserController {

    private UserService userService;

    @RequestMapping(value = "/")
    public String welcomePage() {
        return "/login";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/sign/page")
    public String signPage() {
        return "/sign";
    }

    @ResponseBody
    @RequestMapping(value = "/check/id")
    public int checkIdDup(@RequestParam Map<String, Object> param) {
        return userService.checkIdDup(param);
    }

    @RequestMapping(value = "/sign")
    public String sign(UserDTO userDTO) {
        userService.createMember(userDTO);
        return "/login";
    }

    @RequestMapping(value = "/denied")
    public String denied() {
        return "/denied";
    }
}
