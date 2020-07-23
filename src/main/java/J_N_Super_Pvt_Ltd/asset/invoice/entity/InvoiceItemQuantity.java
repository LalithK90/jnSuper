package J_N_Super_Pvt_Ltd.asset.invoice.entity;


import J_N_Super_Pvt_Ltd.asset.itemBatch.entity.ItemBatch;
import J_N_Super_Pvt_Ltd.util.audit.AuditEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("InvoiceItemQuantity")
public class InvoiceItemQuantity extends AuditEntity {
    private String quantity;

    @ManyToOne
    private ItemBatch itemBatch;

    @ManyToOne
    private Invoice invoice;

}
