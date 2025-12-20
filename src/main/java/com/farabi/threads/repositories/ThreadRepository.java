package com.farabi.threads.repositories;

import com.farabi.threads.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
}
