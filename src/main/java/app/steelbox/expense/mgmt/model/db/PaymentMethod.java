package app.steelbox.expense.mgmt.model.db;

import app.steelbox.expense.mgmt.model.db.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "exp_payment_method")
public class PaymentMethod extends BaseEntity<BaseEntity, Number> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "payment_method", nullable = false, unique = true)
    private String paymentMethod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
