package com.thecoders.cartunnbackend.payment.domain.model.commands;

import java.math.BigDecimal;

public record UpdateCartCommand(Long id, BigDecimal total) {
}
