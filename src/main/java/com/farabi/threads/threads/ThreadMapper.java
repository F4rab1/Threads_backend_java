package com.farabi.threads.threads;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThreadMapper {
    ThreadResponseDto toDto(Thread thread);
}
