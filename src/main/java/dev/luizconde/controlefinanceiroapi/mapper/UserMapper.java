package dev.luizconde.controlefinanceiroapi.mapper;

import dev.luizconde.controlefinanceiroapi.dto.UserRequestDTO;
import dev.luizconde.controlefinanceiroapi.dto.UserResponseDTO;
import dev.luizconde.controlefinanceiroapi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequestDTO dto);

    UserResponseDTO toResponseDto(User user);
}
