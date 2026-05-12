package com.farabi.threads.threads;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ThreadMapper {
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(target = "hasImage", expression = "java(thread.getImageData() != null)")
    @Mapping(target = "imageUrl", expression = "java(thread.getImageData() != null ? \"/threads/\" + thread.getId() + \"/image\" : null)")
    ThreadResponseDto toResponseDto(Thread thread);

    void update(UpdateThreadRequest updateThreadRequest, @MappingTarget Thread thread);
}
