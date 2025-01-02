package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.PaymentRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.PaymentResponse;

import java.util.Optional;

public interface PaymentService {
    Optional<PaymentResponse> save(PaymentRequest paymentRequest);
}
