        package com.gurkha.dtos;
        import lombok.Data;
        import org.springframework.web.multipart.MultipartFile;

        import java.time.LocalDateTime;
        import java.util.List;

        @Data
        public class BlogDto {
            private Long id;
            private MultipartFile image; // API input only
            private String title;
            private String imageName;
            private String imageUrl;
            private String author;
            private LocalDateTime publishedOn;
            private  String description;
            private List<HeaderDto> headers;
        }
