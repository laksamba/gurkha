package com.gurkha.service;

import com.gurkha.dtos.BlogDto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BlogService {


    BlogDto createBlog(BlogDto blogDto);

    List<BlogDto> getAllBlogs();

    Optional< BlogDto> getBlogById(Long id);

    Optional<BlogDto> updateBlog(Long id, BlogDto blogDto);

    boolean deleteBlog(Long id);
}
