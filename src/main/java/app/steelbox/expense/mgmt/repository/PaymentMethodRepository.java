package app.steelbox.expense.mgmt.repository;

import app.steelbox.expense.mgmt.model.db.PaymentMethod;
import app.steelbox.expense.mgmt.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends BaseRepository<PaymentMethod, Integer> {

    PaymentMethod findByPaymentMethod(String paymentMethod);
}
