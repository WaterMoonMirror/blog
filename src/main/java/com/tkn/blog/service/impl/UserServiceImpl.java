package com.tkn.blog.service.impl;

import com.tkn.blog.domain.User;
import com.tkn.blog.repository.UserRepository;
import com.tkn.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public void removeUser(Long id) {

        userRepository.deleteById(id);
    }

    /**
     * 删除列表里面的用户
     *
     * @param users
     * @return
     */
    @Transactional
    @Override
    public void removeUsersInBatch(List<User> users) {
        userRepository.deleteAll(users);

    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    /**
     * 根据用户名进行分页模糊查询
     *
     * @param name
     * @param pageable
     * @return
     */
    @Override
    public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        // 模糊查询
        name = "%" + name + "%";
        return userRepository.findByNameLike(name,pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("username:"+s);
        return userRepository.findByUsername(s);
    }
}
