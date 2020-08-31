package j_n_super_pvt_ltd.asset.PurchaseOrder.dao;


import j_n_super_pvt_ltd.asset.PurchaseOrder.entity.PurchaseOrder;
import j_n_super_pvt_ltd.asset.PurchaseOrder.entity.PurchaseOrderItem;
import j_n_super_pvt_ltd.asset.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PurchaseOrderItemDao extends JpaRepository<PurchaseOrderItem, Integer> {
    PurchaseOrderItem findByPurchaseOrderAndItem(PurchaseOrder purchaseOrder, Item item);
    List<PurchaseOrderItem> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
