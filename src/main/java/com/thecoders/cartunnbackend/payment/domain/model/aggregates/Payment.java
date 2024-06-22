package com.thecoders.cartunnbackend.payment.domain.model.aggregates;


import com.thecoders.cartunnbackend.payment.domain.model.commands.CreatePaymentCommand;
import com.thecoders.cartunnbackend.productRefunds.domain.model.aggregates.ProductRefund;
import com.thecoders.cartunnbackend.productRefunds.domain.model.commands.CreateProductRefundCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", nullable = false)
    private String card_number;

    @Column(name = "expiration_date", nullable = false)
    private String expiration_date;

    @Column(name = "card_holder", nullable = false)
    private String card_holder;

    @Column(name = "cvc", nullable = false)
    private String cvc;

    @Column(name = "method_pay", nullable = false)
    private String method_pay;


    public Payment(){
        this.card_number = Strings.EMPTY;
        this.expiration_date = Strings.EMPTY;
        this.card_holder = Strings.EMPTY;
        this.cvc = Strings.EMPTY;
        this.method_pay = Strings.EMPTY;
    }

    public Payment(String card_number,String expiration_date, String card_holder, String cvc, String method_pay){
        this();
        this.card_number = card_number;
        this.expiration_date = expiration_date;
        this.card_holder = card_holder;
        this.cvc = cvc;
        this.method_pay = method_pay;
    }

    public Payment(CreatePaymentCommand command){
        this();
        this.card_number = command.card_number();
        this.expiration_date = command.expiration_date();
        this.card_holder = command.card_holder();
        this.cvc = command.cvc();
        this.method_pay = command.method_pay();
    }

    public Payment updateInformation(String card_number,String expiration_date, String card_holder, String cvc, String method_pay){
        this.card_number = card_number;
        this.expiration_date = expiration_date;
        this.card_holder = card_holder;
        this.cvc = cvc;
        this.method_pay = method_pay;
        return this;
    }

}
