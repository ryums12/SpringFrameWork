package com.ryums.securityexample.service;

import com.ryums.securityexample.domain.Role;
import com.ryums.securityexample.domain.UserMember;

import com.ryums.securityexample.dto.UserDTO;
import com.ryums.securityexample.entity.UserEntity;
import com.ryums.securityexample.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findById(id);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getName()));

        if(userDTO != null) {
            return new UserMember(userDTO.getId(), userDTO.getPwd(), Role.ADMIN.getName(), authorities);
        } else {
            throw  new InternalAuthenticationServiceException("Member Not Found");
        }
    }

    public void createMember(UserDTO userDTO) {
        userDTO.setPwd(passwordEncoder.encode((CharSequence) userDTO.getPwd()));
        userRepository.save(userDTO.toEntity());
    }

    public int checkIdDup(Map<String, Object> param) {
        return userRepository.countAllById((String) param.get("id"));
    }
}
