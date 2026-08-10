package dev.luizconde.controlefinanceiroapi.mapper;

import dev.luizconde.controlefinanceiroapi.dto.UserRequestDTO;
import dev.luizconde.controlefinanceiroapi.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserUpdateMapper {
    void userUpdate(UserRequestDTO dto,
                    @MappingTarget User user);
}
