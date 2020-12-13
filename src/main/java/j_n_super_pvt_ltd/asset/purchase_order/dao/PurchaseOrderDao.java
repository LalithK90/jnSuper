package j_n_super_pvt_ltd.asset.purchase_order.dao;


import j_n_super_pvt_ltd.asset.purchase_order.entity.enums.PurchaseOrderStatus;
import j_n_super_pvt_ltd.asset.purchase_order.entity.PurchaseOrder;
import j_n_super_pvt_ltd.asset.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderDao extends JpaRepository< PurchaseOrder, Integer> {

    List<PurchaseOrder> findByPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus);

    List<PurchaseOrder> findByPurchaseOrderStatusAndSupplier(PurchaseOrderStatus purchaseOrderStatus, Supplier supplier);


    PurchaseOrder findFirstByOrderByIdDesc();
}
