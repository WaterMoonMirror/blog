package com.tkn.blog.service.impl;

import com.tkn.blog.MyblogApplicationTests;
import com.tkn.blog.domain.User;
import com.tkn.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;

@Slf4j
public class UserServiceImplTest extends MyblogApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    public void listUsersByNameLike() {;
        Page<User> users = userService.listUsersByNameLike("", PageRequest.of(1, 10));
        log.info(""+users.getContent().size());
    }
    @Test
    public void listUs(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode("123456");
        log.info(encodePasswd);
    }
}