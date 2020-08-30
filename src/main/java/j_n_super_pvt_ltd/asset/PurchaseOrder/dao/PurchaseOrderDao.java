package j_n_super_pvt_ltd.asset.PurchaseOrder.dao;



import j_n_super_pvt_ltd.asset.PurchaseOrder.entity.Enum.PurchaseOrderStatus;
import j_n_super_pvt_ltd.asset.PurchaseOrder.entity.PurchaseOrder;
import j_n_super_pvt_ltd.asset.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Integer> {

    List<PurchaseOrder> findByPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus);

    List<PurchaseOrder> findByPurchaseOrderStatusAndSupplier(PurchaseOrderStatus purchaseOrderStatus, Supplier supplier);


    PurchaseOrder findFirstByOrderByIdDesc();
}
