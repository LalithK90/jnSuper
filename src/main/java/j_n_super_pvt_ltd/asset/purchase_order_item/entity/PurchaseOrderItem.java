package j_n_super_pvt_ltd.asset.purchase_order_item.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import j_n_super_pvt_ltd.asset.item.entity.Item;
import j_n_super_pvt_ltd.asset.common_asset.model.enums.LiveOrDead;
import j_n_super_pvt_ltd.asset.purchase_order.entity.PurchaseOrder;
import j_n_super_pvt_ltd.util.audit.AuditEntity;
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
@JsonFilter( "PurchaseOrderItem" )
public class PurchaseOrderItem extends AuditEntity {

    @Column( nullable = false )
    private String quantity;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal buyingPrice;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal lineTotal;

    @Enumerated( EnumType.STRING)
    private LiveOrDead liveOrDead;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    private Item item;

}
