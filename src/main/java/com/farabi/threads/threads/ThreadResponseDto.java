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
}
