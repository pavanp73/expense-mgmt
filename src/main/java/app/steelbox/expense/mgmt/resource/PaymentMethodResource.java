package app.steelbox.expense.mgmt.resource;

import app.steelbox.expense.mgmt.model.db.PaymentMethod;
import app.steelbox.expense.mgmt.service.PaymentMethodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-method")
public class PaymentMethodResource {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodResource(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public List<PaymentMethod> fetchAll() {
        return paymentMethodService.getAllPaymentMethods();
    }

    @PostMapping
    public List<PaymentMethod> createPaymentMethod(@RequestBody List<String> paymentMethodList) {
        return paymentMethodService.createPaymentMethod(paymentMethodList);
    }
}
