package com.ryums.securityexample.service;

import com.ryums.securityexample.dao.UserDAO;
import com.ryums.securityexample.domain.Role;
import com.ryums.securityexample.domain.UserMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Map<String, Object> map = userDAO.selectMember(id);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getName()));

        if(map != null) {
            return new UserMember((String) map.get("id"), (String) map.get("pwd"), Role.ADMIN.getName(), authorities);
        } else {
            throw  new InternalAuthenticationServiceException("Member Not Found");
        }
    }

    public void createMember(Map<String, Object> param) {
        param.put("pwd", passwordEncoder.encode((CharSequence) param.get("pwd")));
        userDAO.createMember(param);
    }

    public int checkIdDup(Map<String, Object> param) {
        return userDAO.checkIdDup(param);
    }
}
