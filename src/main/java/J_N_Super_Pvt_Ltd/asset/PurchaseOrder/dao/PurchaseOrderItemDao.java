package J_N_Super_Pvt_Ltd.asset.PurchaseOrder.dao;


import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.PurchaseOrder;
import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.PurchaseOrderItem;
import J_N_Super_Pvt_Ltd.asset.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PurchaseOrderItemDao extends JpaRepository<PurchaseOrderItem, Integer> {
    PurchaseOrderItem findByPurchaseOrderAndItem(PurchaseOrder purchaseOrder, Item item);

    List<PurchaseOrderItem> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
