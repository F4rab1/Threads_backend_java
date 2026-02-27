package com.farabi.threads.threads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateThreadRequest {
    @NotBlank(message = "Content cannot be empty")
    @Size(max = 300, message = "Content must not exceed 300 characters")
    private String content;
}
