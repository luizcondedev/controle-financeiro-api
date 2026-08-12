package dev.luizconde.controlefinanceiroapi.dto;

import dev.luizconde.controlefinanceiroapi.enums.AccountTypeEnum;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;

public record AccountUpdateRequestDTO(
        String name,
        AccountTypeEnum accountType,
        @Digits(integer = 17, fraction = 2)
        BigDecimal initialBalance
) {
}
