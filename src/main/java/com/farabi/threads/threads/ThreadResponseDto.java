package com.farabi.threads.threads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ThreadResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long authorId;

    private String imageName;
    private String imageType;
    private Boolean hasImage;
    private String imageUrl;
}
