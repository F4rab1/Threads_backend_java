package com.farabi.threads.threads;

import com.farabi.threads.users.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if (thread == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(threadMapper.toResponseDto(thread));
    }

    @PostMapping
    @Operation(summary = "Create a thread.")
    public ResponseEntity<ThreadResponseDto> createThread(@Valid @RequestBody CreateThreadRequest request) {
        Long userId = (Long) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var user = userRepository.findById(userId).orElseThrow();

        var thread = new Thread();
        thread.setContent(request.getContent());
        thread.setAuthor(user);
        thread.setCreatedAt(LocalDateTime.now());

        Thread saved = threadRepository.save(thread);
        ThreadResponseDto response = threadMapper.toResponseDto(saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThreadResponseDto> updateThread(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateThreadRequest request
    ) {
        var thread = threadRepository.findById(id).orElse(null);
        if (thread == null) {
            return ResponseEntity.notFound().build();
        }

        threadMapper.update(request, thread);
        threadRepository.save(thread);

        return ResponseEntity.ok(threadMapper.toResponseDto(thread));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThread(@PathVariable Long id) {
        var thread = threadRepository.findById(id).orElse(null);
        if (thread == null) {
            return ResponseEntity.notFound().build();
        }

        threadRepository.delete(thread);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException exception
    ) {
        var errors = new HashMap<String, String>();

        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}
