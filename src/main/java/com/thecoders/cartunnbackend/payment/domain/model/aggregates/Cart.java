package com.thecoders.cartunnbackend.payment.domain.model.aggregates;

import com.thecoders.cartunnbackend.payment.domain.model.commands.CreateCartCommand;
import jakarta.persistence.Entity;
import lombok.Getter;
import com.thecoders.cartunnbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import java.math.BigDecimal;

@Getter
@Entity
public class Cart extends AuditableAbstractAggregateRoot<Cart>{
    private BigDecimal total;
    public Cart() {
        this.total = BigDecimal.ZERO;
    }

    public Cart(BigDecimal total) {
        this();
        this.total = total;
    }
    public Cart(CreateCartCommand command) {
        this();

        this.total = command.total();
    }
    public Cart updateInformation(BigDecimal total) {
        this.total = total;
        return this;
    }
}
