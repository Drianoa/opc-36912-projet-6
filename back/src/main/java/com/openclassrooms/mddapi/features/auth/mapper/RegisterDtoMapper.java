package com.openclassrooms.mddapi.features.auth.mapper;

import com.openclassrooms.mddapi.features.auth.User;
import com.openclassrooms.mddapi.features.auth.dto.RegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface RegisterDtoMapper extends Converter<RegisterDto, User> {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "topics")
    @Mapping(ignore = true, target = "authorities")
    User convert(RegisterDto registerDto);
}
