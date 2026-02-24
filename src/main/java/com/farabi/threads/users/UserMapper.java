package com.farabi.threads.users;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);
    User toEntity(RegisterUserRequestDto registerUserRequestDto);
    void update(UpdateUserRequestDto updateUserRequestDto, @MappingTarget User user);
}
