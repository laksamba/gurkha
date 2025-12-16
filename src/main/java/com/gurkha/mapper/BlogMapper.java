package com.gurkha.mapper;

import com.gurkha.dtos.BlogDto;
import com.gurkha.dtos.HeaderDto;
import com.gurkha.entities.Blog;
import com.gurkha.entities.Header;
import com.gurkha.entities.Paragaraph;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BlogMapper {


    public static BlogDto toDto(Blog blog) {
        BlogDto dto = new BlogDto();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setAuthor(blog.getAuthor());
        dto.setPublishedOn(blog.getPublishedOn());
        dto.setImageName(blog.getImageName());
        dto.setDescription(blog.getDescription());
        dto.setImageUrl(blog.getImageUrl());

        List<HeaderDto> headers = blog.getHeaders().stream().map(h -> {
            HeaderDto hdto = new HeaderDto();
            hdto.setId(h.getId());
            hdto.setHeaderLabel(h.getHeaderLabel());
            hdto.setHeaderTitle(h.getHeaderTitle());

            List<String> paragraphs = h.getParagraphs().stream()
                    .map(Paragaraph::getParagraphText)
                    .collect(Collectors.toList());

            hdto.setParagraphs(paragraphs);
            return hdto;
        }).collect(Collectors.toList());

        dto.setHeaders(headers);
        return dto;
    }
    
    public static Blog toEntity(String name, String url, BlogDto dto) {
        Blog blog = new Blog();
        blog.setId(dto.getId());
        blog.setTitle(dto.getTitle());
        blog.setAuthor(dto.getAuthor());
        blog.setDescription(dto.getDescription());
        blog.setPublishedOn(dto.getPublishedOn());
        blog.setImageName(name != null ? name : null);
        blog.setImageUrl(url != null ? name : null);

        // Convert Headers
        List<Header> headers = dto.getHeaders().stream().map(hdto -> {
            Header header = new Header();
            header.setHeaderLabel(hdto.getHeaderLabel());
            header.setHeaderTitle(hdto.getHeaderTitle());
            header.setBlog(blog);

            // Convert Paragraphs from List<String>
            List<Paragaraph> paragraphList = hdto.getParagraphs().stream()
                    .map(text -> {
                        Paragaraph p = new Paragaraph();
                        p.setParagraphText(text);
                        p.setHeader(header);
                        return p;
                    })
                    .collect(Collectors.toList());
            header.setParagraphs(paragraphList);
            return header;
        }).collect(Collectors.toList());
        blog.setHeaders(headers);
        return blog;
    }

    public static Blog mapUpdate(String imageName, String imageUrl, BlogDto dto, Blog existing) {

        //  BASIC FIELDS
        existing.setTitle(dto.getTitle());
        existing.setAuthor(dto.getAuthor());
        existing.setDescription(dto.getDescription());
        existing.setPublishedOn(existing.getPublishedOn()); // keep old date

        if (imageName != null && imageUrl != null) {
            existing.setImageName(imageName);
            existing.setImageUrl(imageUrl);
        }


        // HEADER UPDATE 
        Map<Long, Header> existingHeaderMap = existing.getHeaders()
                .stream()
                .collect(Collectors.toMap(Header::getId, h -> h));

        List<Header> finalHeaders = new ArrayList<>();


        for (HeaderDto hdto : dto.getHeaders()) {

            Header header;
            //  existing header 
            if (hdto.getId() != null && existingHeaderMap.containsKey(hdto.getId())) {
                header = existingHeaderMap.get(hdto.getId());
                header.setHeaderLabel(hdto.getHeaderLabel());
                header.setHeaderTitle(hdto.getHeaderTitle());
            }
        //    new header
            else {
                header = new Header();
                header.setHeaderLabel(hdto.getHeaderLabel());
                header.setHeaderTitle(hdto.getHeaderTitle());
                header.setBlog(existing);
            }

           
            List<Paragaraph> existingParas = header.getParagraphs();
            List<Paragaraph> finalParas = new ArrayList<>();

            int index = 0;

            for (String ptext : hdto.getParagraphs()) {

                Paragaraph p;

                // Update  paragraph 
                if (existingParas != null && existingParas.size() > index) {
                    p = existingParas.get(index);
                    p.setParagraphText(ptext);
                }

                // Create new paragraph
                else {
                    p = new Paragaraph();
                    p.setParagraphText(ptext);
                    p.setHeader(header);
                }

                finalParas.add(p);
                index++;
            }

  
            header.getParagraphs().clear();
            header.getParagraphs().addAll(finalParas);

            finalHeaders.add(header);
        }


        existing.getHeaders().clear();
        existing.getHeaders().addAll(finalHeaders);

        return existing;
    }
}


