package com.farabi.threads.users;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileResponseDto toDto(Profile profile);
}
