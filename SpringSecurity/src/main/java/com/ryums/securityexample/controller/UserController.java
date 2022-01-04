package com.ryums.securityexample.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ryums.securityexample.domain.GoogleOAuthRequest;
import com.ryums.securityexample.domain.GoogleOAuthResponse;
import com.ryums.securityexample.dto.UserDTO;
import com.ryums.securityexample.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    private UserService userService;

    final static String GOOGLE_TOKEN_BASE_URL = "https://oauth2.googleapis.com/token";
    final static String GOOGLE_REVOKE_TOKEN_BASE_URL = "https://oauth2.googleapis.com/revoke";

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/")
    public ModelAndView welcomePage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/sign/page")
    public ModelAndView signPage() {
        return new ModelAndView("sign");
    }

    @ResponseBody
    @RequestMapping(value = "/check/id")
    public int checkIdDup(@RequestParam Map<String, Object> param) {
        return userService.checkIdDup(param);
    }

    @RequestMapping(value = "/sign")
    public ModelAndView sign(UserDTO userDTO) {
        userService.createMember(userDTO);
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/denied")
    public ModelAndView denied() {
        return new ModelAndView("denied");
    }
}
