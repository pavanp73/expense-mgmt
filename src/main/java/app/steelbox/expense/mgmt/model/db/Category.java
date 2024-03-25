package app.steelbox.expense.mgmt.model.db;

import app.steelbox.expense.mgmt.model.db.base.BaseEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exp_category")
public class Category extends BaseEntity<BaseEntity, Number> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private TypeLookup typeLookup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeLookup getTypeLookup() {
        return typeLookup;
    }

    public void setTypeLookup(TypeLookup typeLookup) {
        this.typeLookup = typeLookup;
    }
}
