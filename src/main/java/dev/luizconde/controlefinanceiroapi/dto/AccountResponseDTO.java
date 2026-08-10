package dev.luizconde.controlefinanceiroapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.luizconde.controlefinanceiroapi.enums.AccountStatusEnum;
import dev.luizconde.controlefinanceiroapi.enums.AccountTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponseDTO(
        Long id,
        String name,
        AccountTypeEnum accountType,
        BigDecimal initialBalance,
        BigDecimal currentBalance,
        Long userId,
        AccountStatusEnum accountStatus,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createDate
) {
}
