package dev.luizconde.controlefinanceiroapi.mapper;

import dev.luizconde.controlefinanceiroapi.dto.AccountRequestDTO;
import dev.luizconde.controlefinanceiroapi.dto.AccountResponseDTO;
import dev.luizconde.controlefinanceiroapi.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountRequestDTO requestDTO);

    @Mapping(source = "user.id", target = "userId")
    AccountResponseDTO toResponse(Account account);
}
