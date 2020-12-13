package j_n_super_pvt_ltd.asset.purchase_order_item.dao;


import j_n_super_pvt_ltd.asset.item.entity.Item;
import j_n_super_pvt_ltd.asset.purchase_order_item.entity.PurchaseOrderItem;
import j_n_super_pvt_ltd.asset.purchase_order.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderItemDao extends JpaRepository<PurchaseOrderItem, Integer> {
    PurchaseOrderItem findByPurchaseOrderAndItem(PurchaseOrder purchaseOrder, Item item);
    List<PurchaseOrderItem> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
