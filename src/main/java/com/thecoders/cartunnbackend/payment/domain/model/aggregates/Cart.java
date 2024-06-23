package com.thecoders.cartunnbackend.payment.domain.model.aggregates;

import com.thecoders.cartunnbackend.payment.domain.model.commands.CreateCartCommand;
import com.thecoders.cartunnbackend.product.domain.model.aggregates.Product;
import jakarta.persistence.*;
import lombok.Getter;
import com.thecoders.cartunnbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "cart")
public class Cart extends AuditableAbstractAggregateRoot<Cart>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total", nullable = false)

    private BigDecimal total;

    @ManyToMany
    @JoinTable(name = "cart_product",
    joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> assignedProducts= new HashSet<>();
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
