package com.ryums.securityexample.service;

import com.ryums.securityexample.dao.MainDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    private MainDAO mainDAO;

    /** write service */
}
