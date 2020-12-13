package j_n_super_pvt_ltd.asset.purchaseOrder.dao;


import j_n_super_pvt_ltd.asset.purchaseOrder.entity.PurchaseOrder;
import j_n_super_pvt_ltd.asset.purchaseOrder.entity.PurchaseOrderItem;
import j_n_super_pvt_ltd.asset.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PurchaseOrderItemDao extends JpaRepository<PurchaseOrderItem, Integer> {
    PurchaseOrderItem findByPurchaseOrderAndItem(PurchaseOrder purchaseOrder, Item item);
    List<PurchaseOrderItem> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
