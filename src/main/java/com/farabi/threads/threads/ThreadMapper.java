package com.farabi.threads.threads;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ThreadMapper {
    @Mapping(source = "author.id", target = "authorId")
    ThreadResponseDto toResponseDto(Thread thread);
    void update(UpdateThreadRequest updateThreadRequest, @MappingTarget Thread thread);
}
