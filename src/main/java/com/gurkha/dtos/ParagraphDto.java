package com.gurkha.dtos;

import com.gurkha.entities.Header;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ParagraphDto {
    private Long id;

    private String paragraphText;

    private Header header;
}
