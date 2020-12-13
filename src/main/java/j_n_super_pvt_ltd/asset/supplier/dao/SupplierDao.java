package j_n_super_pvt_ltd.asset.supplier.dao;

import j_n_super_pvt_ltd.asset.supplier_item.entity.enums.ItemSupplierStatus;
import j_n_super_pvt_ltd.asset.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SupplierDao extends JpaRepository<Supplier, Integer> {
    Supplier findFirstByOrderByIdDesc();

    Supplier findByIdAndItemSupplierStatus(Integer supplierId, ItemSupplierStatus itemSupplierStatus);
}
