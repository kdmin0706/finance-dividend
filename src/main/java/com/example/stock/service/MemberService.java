package com.example.stock.service;

import com.example.stock.exception.impl.AlreadyExistUserException;
import com.example.stock.model.constant.Auth;
import com.example.stock.persist.MemberRepository;
import com.example.stock.persist.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface MemberService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    MemberEntity register(Auth.SignUp member);

    MemberEntity authenticate(Auth.SignIn member);

}
