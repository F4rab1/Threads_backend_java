package com.farabi.threads.threads;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/threads")
public class ThreadController {
    private final ThreadRepository threadRepository;
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
}
