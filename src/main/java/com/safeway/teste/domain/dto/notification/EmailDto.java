package com.safeway.teste.domain.dto.notification;

import java.math.BigDecimal;

public record EmailDto(
        String name,
        BigDecimal amount,
        String to
) {
}


