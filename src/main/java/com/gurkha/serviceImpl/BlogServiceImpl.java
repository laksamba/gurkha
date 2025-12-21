package com.gurkha.serviceImpl;

import com.gurkha.dtos.ApplicationDto;
import com.gurkha.dtos.BlogDto;
import com.gurkha.dtos.HeaderDto;
import com.gurkha.entities.Blog;
import com.gurkha.entities.Header;
import com.gurkha.entities.Paragaraph;
import com.gurkha.fileHadling.FilePutGet;
import com.gurkha.mapper.BlogMapper;
import com.gurkha.repository.BlogRepo;
import com.gurkha.repository.HeaderRepo;
import com.gurkha.repository.ParagaraphRepo;
import com.gurkha.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepo blogRepository;
    private  final FilePutGet fileService;
    private final CloudinaryService cloudinaryService;
    private final HeaderRepo headerRepository;
    private final ParagaraphRepo paragraphRepository;


    @Override
    public BlogDto createBlog(BlogDto dto) {
         Map uploadResult = cloudinaryService.upload(dto.getImage(), "blogs");
        String fileName=null;
        String fullPath=null; 
        // Extract file information
        if(uploadResult !=null){
         fileName = (String) uploadResult.get("original_filename");
         fullPath = (String) uploadResult.get("url");
        // Save image
        }
        System.out.println("run of the this ");
        Blog saved = blogRepository.save(BlogMapper.toEntity(fileName,fullPath,dto)); 
        return BlogMapper.toDto(saved);
    }

    @Override
    public List<BlogDto> getAllBlogs() {
        return blogRepository.findAll()
                .stream().map(BlogMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BlogDto> getBlogById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        return Optional.of(BlogMapper.toDto(blog));
    }


    @Override
    public Optional<BlogDto> updateBlog(Long id, BlogDto dto) {
        Blog blog = blogRepository.findById(id)

                .orElseThrow(() -> new RuntimeException("Blog not found"));

        String fileName = blog.getImageName();
        String fullPath = blog.getImageUrl();

        

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
              Map uploadResult = cloudinaryService.upload(dto.getImage(), "blogs");
        // Extract file information
        if(uploadResult !=null){
         fileName = (String) uploadResult.get("original_filename");
         fullPath = (String) uploadResult.get("url");
        // Save image
        }
        }

        Blog updated = blogRepository.save(BlogMapper.mapUpdate(fileName,fullPath, dto,blog));
        return Optional.of(BlogMapper.toDto(updated));
    }
    @Override
    public boolean deleteBlog(Long id) {
        if (!blogRepository.existsById(id)) return false;
        blogRepository.deleteById(id);
        return true;
    }
}
