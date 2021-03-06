package com.tkn.blog.repository;

import com.tkn.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户仓库
 */
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * 根据用户名分页查询用户列表
     * @param name
     * @param pageable
     * @return
     */
    Page<User> findByNameLike(String name, Pageable pageable);


    User findByUsername(String username);
    void deleteById(Long id);
}
