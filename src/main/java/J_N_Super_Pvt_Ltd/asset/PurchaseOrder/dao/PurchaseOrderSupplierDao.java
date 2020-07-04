package J_N_Super_Pvt_Ltd.asset.PurchaseOrder.dao;



import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.PurchaseOrderSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderSupplierDao extends JpaRepository<PurchaseOrderSupplier, Integer> {
}
