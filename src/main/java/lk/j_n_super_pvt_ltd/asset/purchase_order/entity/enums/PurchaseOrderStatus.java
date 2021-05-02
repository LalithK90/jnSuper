package lk.j_n_super_pvt_ltd.asset.purchase_order.entity.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PurchaseOrderStatus {

    NOT_PROCEED(" Not Completed"),
    NOT_COMPLETED(" Not Proceed"),
    COMPLETED(" Completed ");

    private final String purchaseOrderStatus;

}
