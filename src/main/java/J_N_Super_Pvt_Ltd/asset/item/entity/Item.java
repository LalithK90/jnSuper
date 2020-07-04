package J_N_Super_Pvt_Ltd.asset.item.entity;


import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.PurchaseOrderSupplier;
import J_N_Super_Pvt_Ltd.asset.item.category.entity.Category;

import J_N_Super_Pvt_Ltd.asset.supplier.entity.SupplierItem;
import J_N_Super_Pvt_Ltd.util.audit.AuditEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Item")
public class Item extends AuditEntity {

    @Size(min = 5, message = "Your name cannot be accepted")
    private String name;

    private Integer rop;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    /*@ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;*/

/*    @OneToMany(mappedBy = "item")
    private List<PurchaseOrderSupplier> purchaseOrderItems;*/

/*    @OneToMany(mappedBy = "item")
    private List<ItemBatch> itemBatches;*/

    @OneToMany(mappedBy = "item")
    private List<SupplierItem> supplierItems;
}
