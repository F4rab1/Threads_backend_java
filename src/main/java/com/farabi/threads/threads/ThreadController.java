package com.farabi.threads.threads;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/threads")
public class ThreadController {
    private final ThreadRepository threadRepository;

}
