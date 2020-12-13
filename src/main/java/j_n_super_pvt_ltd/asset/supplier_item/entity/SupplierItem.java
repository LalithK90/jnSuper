package j_n_super_pvt_ltd.asset.supplier_item.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import j_n_super_pvt_ltd.asset.item.entity.Item;
import j_n_super_pvt_ltd.asset.supplier_item.entity.enums.ItemSupplierStatus;
import j_n_super_pvt_ltd.util.audit.AuditEntity;
import j_n_super_pvt_ltd.asset.common_asset.model.enums.LiveOrDead;
import j_n_super_pvt_ltd.asset.supplier.entity.Supplier;
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
@JsonFilter("SupplierItem")
public class SupplierItem extends AuditEntity {

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ItemSupplierStatus itemSupplierStatus;

    @Enumerated(EnumType.STRING)
    private LiveOrDead liveOrDead;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Supplier supplier;

}
