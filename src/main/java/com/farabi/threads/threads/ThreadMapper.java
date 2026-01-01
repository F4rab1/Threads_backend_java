package com.farabi.threads.threads;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThreadMapper {
    @Mapping(source = "author.id", target = "authorId")
    ThreadResponseDto toResponseDto(Thread thread);
}
