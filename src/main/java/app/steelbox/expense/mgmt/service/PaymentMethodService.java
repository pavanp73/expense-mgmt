package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.db.PaymentMethod;
import app.steelbox.expense.mgmt.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public PaymentMethod findByPaymentMethod(String paymentMethod) {
        return paymentMethodRepository.findByPaymentMethod(paymentMethod);
    }

    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    public List<PaymentMethod> createPaymentMethod(List<String> paymentMethodList) {
        return paymentMethodRepository.saveAll(
                paymentMethodList.stream().map(method -> {
                    PaymentMethod paymentMethod = new PaymentMethod();
                    paymentMethod.setPaymentMethod(method);
                    return paymentMethod;
                }).collect(Collectors.toList())
        );
    }
}
