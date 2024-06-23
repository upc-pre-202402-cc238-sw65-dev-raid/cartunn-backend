package com.thecoders.cartunnbackend.payment.application.internal.commandservices;


import com.thecoders.cartunnbackend.payment.domain.model.aggregates.Payment;
import com.thecoders.cartunnbackend.payment.domain.model.commands.CreatePaymentCommand;
import com.thecoders.cartunnbackend.payment.domain.model.commands.UpdatePaymentCommand;
import com.thecoders.cartunnbackend.payment.domain.services.PaymentCommandService;
import com.thecoders.cartunnbackend.payment.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {
    private final PaymentRepository paymentRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Long handle(CreatePaymentCommand command) {
        if (paymentRepository.existsByCardHolder(command.cardHolder())) {
            throw new IllegalArgumentException("Payment with title " + command.cardHolder() + " already exists");
        }
        var payment = new Payment(command);
        try {
            paymentRepository.save(payment);
            return payment.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving payment: " + e.getMessage());
        }
    }


    @Override
    public Optional<Payment> handle(UpdatePaymentCommand command){
        if(paymentRepository.existsByCardHolderAndIdIsNot(command.cardHolder(), command.paymentId())){
            throw new IllegalArgumentException("Profile with same payment already exists");
        }
        var result = paymentRepository.findById(command.paymentId());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Payment does not exist");
        }
        var paymentToUpdated = result.get();
        try {
            paymentToUpdated.updateInformation(command.cardNumber(), command.expirationDate(), command.cardHolder(),command.cvc(),command.methodPay());
            var updatedProfile = paymentRepository.save(paymentToUpdated);
            return Optional.of(updatedProfile);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while updating payment: " + e.getMessage());
        }
    }
}
