package app.steelbox.expense.mgmt.model.db.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

import java.time.Instant;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_ts", nullable = false)
    private Long createdTS;

    @Column(name = "updated_ts", nullable = false)
    private Long updatedTS;

    @PrePersist
    public void setUp() {
        if (createdTS == null) {
            createdTS = Instant.now().toEpochMilli();
        }
        updatedTS = Instant.now().toEpochMilli();
    }

    public Long getCreatedTS() {
        return createdTS;
    }

    public void setCreatedTS(Long createdTS) {
        this.createdTS = createdTS;
    }

    public Long getUpdatedTS() {
        return updatedTS;
    }

    public void setUpdatedTS(Long updatedTS) {
        this.updatedTS = updatedTS;
    }
}
