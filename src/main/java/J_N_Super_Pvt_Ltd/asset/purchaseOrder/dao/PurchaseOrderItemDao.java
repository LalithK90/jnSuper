package lk.J_N_Super_Pvt_Ltd.asset.purchaseOrder.dao;


import lk.J_N_Super_Pvt_Ltd.asset.purchaseOrder.entity.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderItemDao extends JpaRepository<PurchaseOrderItem, Integer> {
}
