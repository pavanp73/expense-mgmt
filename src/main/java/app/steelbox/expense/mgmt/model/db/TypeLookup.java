package app.steelbox.expense.mgmt.model.db;

import app.steelbox.expense.mgmt.model.db.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "exp_type_lookup")
public class TypeLookup extends BaseEntity<BaseEntity, Number> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", nullable = false, unique = true)
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
