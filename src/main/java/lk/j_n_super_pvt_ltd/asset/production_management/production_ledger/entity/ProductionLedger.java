package lk.j_n_super_pvt_ltd.asset.production_management.production_ledger.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import lk.j_n_super_pvt_ltd.asset.ledger.entity.Ledger;
import lk.j_n_super_pvt_ltd.asset.production_management.production.entity.Production;
import lk.j_n_super_pvt_ltd.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("InvoiceLedger")
public class ProductionLedger extends AuditEntity {

    @Column(nullable = false)
    private String quantity;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal sellPrice;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal lineTotal;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal discountAmount;

    @ManyToOne
    private Ledger ledger;

    @ManyToOne
    private Production production;

}
