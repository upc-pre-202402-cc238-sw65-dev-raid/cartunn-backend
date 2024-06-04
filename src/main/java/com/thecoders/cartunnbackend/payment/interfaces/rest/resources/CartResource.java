package com.thecoders.cartunnbackend.payment.interfaces.rest.resources;

import java.math.BigDecimal;

public record CartResource(Long id, BigDecimal total) {
}
