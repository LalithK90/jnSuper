package lk.J_N_Super_Pvt_Ltd.asset.purchaseOrder.dao;


import lk.J_N_Super_Pvt_Ltd.asset.purchaseOrder.entity.Enum.PurchaseOrderStatus;
import lk.J_N_Super_Pvt_Ltd.asset.purchaseOrder.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Integer> {
    List<PurchaseOrder> findByPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus);

    PurchaseOrder findFirstByOrderByIdDesc();
}
