package dev.luizconde.controlefinanceiroapi.dto;

import dev.luizconde.controlefinanceiroapi.enums.AccountStatusEnum;

public record UpdateStatusAccountDTO(
        AccountStatusEnum accountStatusEnum
) {
}
