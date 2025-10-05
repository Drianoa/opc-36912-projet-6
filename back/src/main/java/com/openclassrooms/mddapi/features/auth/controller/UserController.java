package com.openclassrooms.mddapi.features.auth.controller;


import com.openclassrooms.mddapi.features.auth.UserService;
import com.openclassrooms.mddapi.features.auth.dto.UserResponseDto;
import com.openclassrooms.mddapi.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User management")
public class UserController {

    private final UserService userService;
    private final ConversionService conversionService;


    /**
     * Fetches a user by its id.
     *
     * @param id the id of the user
     * @return the user entity as a DTO
     */
    @GetMapping("{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Integer id) {
        User userEntity = userService.findById(id);
        UserResponseDto responseDto = conversionService.convert(userEntity, UserResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }
}
