package com.farabi.threads.threads;

import com.farabi.threads.users.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/threads")
@Tag(name = "Threads")
public class ThreadController {
    private final ThreadRepository threadRepository;
    private final UserRepository userRepository;
    private final ThreadMapper threadMapper;

    @GetMapping
    @Operation(summary = "Get all threads.")
    public ResponseEntity<List<ThreadResponseDto>> getAllThreads() {
        var threads = threadRepository.findAll()
                .stream()
                .map(threadMapper::toResponseDto)
                .toList();

        return ResponseEntity.ok(threads);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a thread by id.")
    public ResponseEntity<ThreadResponseDto> getThreadById(@PathVariable Long id) {
        var thread = threadRepository.findById(id).orElse(null);

        return ResponseEntity.ok(threadMapper.toResponseDto(thread));
    }

    @PostMapping
    @Operation(summary = "Create a thread.")
    public ResponseEntity<ThreadResponseDto> createThread(@RequestBody CreateThreadRequest request) {
        Long authorId = 1L;  // I need to change
        var user = userRepository.findById(authorId).orElse(null);
        System.out.println(user.getProfile().getUsername());

        var thread = new Thread();
        thread.setContent(request.getContent());
        thread.setAuthor(user);
        thread.setCreatedAt(LocalDateTime.now());

        Thread saved = threadRepository.save(thread);
        ThreadResponseDto response = threadMapper.toResponseDto(saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
