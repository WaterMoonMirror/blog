package com.tkn.blog.service.impl;

import com.tkn.blog.domain.Blog;
import com.tkn.blog.domain.User;
import com.tkn.blog.repository.BlogRepository;
import com.tkn.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    /**
     * 保存Blog
     *
     * @param blog@return
     */
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    /**
     * 删除Blog
     *
     * @param id
     * @return
     */
    @Override
    public void removeBlog(Long id) {
        blogRepository.deleteById(id);
    }

    /**
     * 更新Blog
     *
     * @param blog@return
     */
    @Override
    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    /**
     * 根据id获取Blog
     *
     * @param id
     * @return
     */
    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.getOne(id);
    }

    /**
     * 根据用户名进行分页模糊查询（最新）
     *
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogsByTitleLike(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        return blogRepository.findByUserAndTitleLike(user,title,pageable);
    }

    /**
     * 根据用户名进行分页模糊查询（最热）
     *
     * @param suser
     * @param title
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogsByTitleLikeAndSort(User suser, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        return blogRepository.findByUserAndTitleLikeOrderByCreateTimeDesc(suser,title,pageable);
    }

    /**
     * 阅读量递增
     *
     * @param id
     */
    @Override
    public void readingIncrease(Long id) {
        Blog blog = blogRepository.getOne(id);
        blog.setReading(blog.getReading()+1);
        blogRepository.save(blog);
    }
}
