package com.farabi.threads.threads;

import com.farabi.threads.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/threads")
public class ThreadController {
    private final ThreadRepository threadRepository;
    private final UserRepository userRepository;
    private final ThreadMapper threadMapper;

    @GetMapping
    public ResponseEntity<List<ThreadResponseDto>> getAllThreads() {
        var threads = threadRepository.findAll()
                .stream()
                .map(threadMapper::toResponseDto)
                .toList();

        return ResponseEntity.ok(threads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThreadResponseDto> getThreadById(@PathVariable Long id) {
        var thread = threadRepository.findById(id).orElse(null);

        return ResponseEntity.ok(threadMapper.toResponseDto(thread));
    }

    @PostMapping
    public ResponseEntity<ThreadResponseDto> createThread(@RequestBody CreateThreadRequest request) {
        Long authorId = 1L;  // I need to change
        var user = userRepository.findById(authorId).orElse(null);

        var thread = new Thread();
        thread.setContent(request.getContent());
        thread.setAuthor(user);
        thread.setCreatedAt(LocalDateTime.now());

        Thread saved = threadRepository.save(thread);
        ThreadResponseDto response = threadMapper.toResponseDto(saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
