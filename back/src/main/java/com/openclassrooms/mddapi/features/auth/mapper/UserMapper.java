package com.openclassrooms.mddapi.features.auth.mapper;

import com.openclassrooms.mddapi.features.auth.dto.UserResponseDto;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface UserMapper extends Converter<User, UserResponseDto> {
}
