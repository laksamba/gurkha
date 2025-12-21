package com.gurkha.controller;
import com.gurkha.dtos.BlogDto;
import com.gurkha.exception.ResourceNotFoundException;
import com.gurkha.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/blog")
@AllArgsConstructor
public class BlogController {
    private final BlogService blogService; // Assume service layer exists

    // CREATE
    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@ModelAttribute BlogDto blogDto) {
        BlogDto createdBlog = blogService.createBlog(blogDto);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }


    // READ ALL
    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        List<BlogDto> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable Long id) {
        BlogDto blog = blogService.getBlogById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));
        return ResponseEntity.ok(blog);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(
            @PathVariable Long id,
            @ModelAttribute BlogDto blogDto) {

        BlogDto updatedBlog = blogService.updateBlog(id, blogDto).orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));

        return ResponseEntity.ok(updatedBlog);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBlog(@PathVariable Long id) {
        boolean deleted = blogService.deleteBlog(id);
        if (!deleted) {
            throw new ResourceNotFoundException("Blog not found with id: " + id);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Blog deleted successfully");
        return ResponseEntity.ok(response);
    }
}