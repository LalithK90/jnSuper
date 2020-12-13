package j_n_super_pvt_ltd.asset.item.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import j_n_super_pvt_ltd.asset.purchaseOrder.entity.PurchaseOrderItem;
import j_n_super_pvt_ltd.asset.category.entity.Category;
import j_n_super_pvt_ltd.asset.item.entity.Enum.ItemStatus;
import j_n_super_pvt_ltd.asset.ledger.entity.Ledger;
import j_n_super_pvt_ltd.asset.supplierItem.entity.SupplierItem;
import j_n_super_pvt_ltd.util.audit.AuditEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter( "Item" )
public class Item extends AuditEntity {

    @Size( min = 5, message = "Your name cannot be accepted" )
    private String name;

    @NotEmpty
    private String rop;

    @Column( unique = true )
    private String code;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal sellPrice;

    @Enumerated( EnumType.STRING )
    private ItemStatus itemStatus;

    @ManyToOne
    private Category category;

    @OneToMany( mappedBy = "item" )
    private List< SupplierItem > supplierItems;

    @OneToMany( mappedBy = "item" )
    @JsonBackReference
    private List< Ledger > ledgers;

    @OneToMany( mappedBy = "item" )
    private List< PurchaseOrderItem > purchaseOrderItems;
}
