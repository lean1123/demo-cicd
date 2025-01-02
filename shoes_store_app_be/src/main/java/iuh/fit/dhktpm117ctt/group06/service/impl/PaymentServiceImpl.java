package iuh.fit.dhktpm117ctt.group06.service.impl;

import iuh.fit.dhktpm117ctt.group06.dto.request.PaymentRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.PaymentResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Payment;
import iuh.fit.dhktpm117ctt.group06.repository.PaymentRepository;
import iuh.fit.dhktpm117ctt.group06.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private ModelMapper modelMapper= new ModelMapper();

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    private PaymentResponse mapToPaymentResponse(Payment payment) {return modelMapper.map(payment, PaymentResponse.class);}
    private Payment mapToPayment(PaymentRequest paymentRequest) {return modelMapper.map(paymentRequest, Payment.class);}

    @Override
    public Optional<PaymentResponse> save(PaymentRequest paymentRequest) {
        Payment payment = mapToPayment(paymentRequest);
        return Optional.of(mapToPaymentResponse(paymentRepository.save(payment)));
    }
}
