package dev.luizconde.controlefinanceiroapi.mapper;

import dev.luizconde.controlefinanceiroapi.dto.AccountRequestDTO;
import dev.luizconde.controlefinanceiroapi.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountUpdateMapper {
    void updateAccount(AccountRequestDTO requestDTO,
                       @MappingTarget Account account);
}
