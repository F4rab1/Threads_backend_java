package com.farabi.threads.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProfileResponseDto {
    private String username;
    private String bio;
}
