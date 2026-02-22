package com.farabi.threads.users;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        var users = userRepository.findAll()
                .stream()
                .map(user -> {
                    ProfileResponseDto profileResponseDto = null;

                    if (user.getProfile() != null) {
                        profileResponseDto = new ProfileResponseDto(
                            user.getProfile().getUsername(),
                            user.getProfile().getBio()
                        );
                    }

                    return new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole().toString(),
                        profileResponseDto
                    );
                })
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        ProfileResponseDto profileResponseDto = null;

        if (user.getProfile() != null) {
            profileResponseDto = new ProfileResponseDto(
                user.getProfile().getUsername(),
                user.getProfile().getBio()
            );
        }

        UserResponseDto userResponseDto = new UserResponseDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole().toString(),
            profileResponseDto
        );

        return ResponseEntity.ok(userResponseDto);
    }
}
