package j_n_super_pvt_ltd.asset.invoice_item.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import j_n_super_pvt_ltd.asset.item.entity.Item;
import j_n_super_pvt_ltd.util.audit.AuditEntity;
import j_n_super_pvt_ltd.asset.common_asset.model.enums.LiveOrDead;
import j_n_super_pvt_ltd.asset.invoice.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("InvoiceItem")
public class InvoiceItem extends AuditEntity {

    @Column(nullable = false)
    private String quantity;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal sellPrice;

    @Enumerated( EnumType.STRING)
    private LiveOrDead liveOrDead;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Invoice invoice;

}
