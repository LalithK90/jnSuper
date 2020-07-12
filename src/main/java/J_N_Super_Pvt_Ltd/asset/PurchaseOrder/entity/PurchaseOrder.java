package J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity;

import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.Enum.PurchaseOrderStatus;
import J_N_Super_Pvt_Ltd.asset.payment.entity.Payment;
import J_N_Super_Pvt_Ltd.asset.supplier.entity.Supplier;
import J_N_Super_Pvt_Ltd.util.audit.AuditEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("PurchaseOrder")
public class PurchaseOrder extends AuditEntity {

    private String remark;

    @Size(min = 5, message = "Your Company name cannot be accepted")
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

   @ManyToOne
    private Supplier supplier;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.PERSIST)
    private List<PurchaseOrderItem> purchaseOrderItems;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus purchaseOrderStatus;


    @OneToMany(mappedBy = "purchaseOrder")
    private List<Payment> payments;

}
