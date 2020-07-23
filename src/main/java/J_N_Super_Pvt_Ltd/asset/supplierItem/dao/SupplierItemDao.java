package J_N_Super_Pvt_Ltd.asset.supplierItem.dao;

import J_N_Super_Pvt_Ltd.asset.item.entity.Item;
import J_N_Super_Pvt_Ltd.asset.supplier.entity.Supplier;
import J_N_Super_Pvt_Ltd.asset.supplierItem.entity.Enum.ItemSupplierStatus;
import J_N_Super_Pvt_Ltd.asset.supplierItem.entity.SupplierItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierItemDao extends JpaRepository<SupplierItem, Integer> {
    SupplierItem findBySupplierAndItem(Supplier supplier, Item item);

    List<SupplierItem> findBySupplier(Supplier supplier);

    List<SupplierItem> findBySupplierAndItemSupplierStatus(Supplier supplier, ItemSupplierStatus itemSupplierStatus);

    List<SupplierItem> findByItem(Item item);
}
