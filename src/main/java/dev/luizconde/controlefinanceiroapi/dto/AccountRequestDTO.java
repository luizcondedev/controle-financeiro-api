package dev.luizconde.controlefinanceiroapi.dto;

import dev.luizconde.controlefinanceiroapi.enums.AccountTypeEnum;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountRequestDTO(
        @NotBlank(message = "Name field is required.")
        String name,
        @NotNull(message = "Account type is required")
        AccountTypeEnum accountType,
        @NotNull(message = "Initial Balance is required")
        @Digits(integer = 17, fraction = 2)
        BigDecimal initialBalance
) {
}
