package j_n_super_pvt_ltd.asset.supplier.dao;


import j_n_super_pvt_ltd.asset.supplier.entity.Supplier;
import j_n_super_pvt_ltd.asset.supplierItem.entity.Enum.ItemSupplierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SupplierDao extends JpaRepository<Supplier, Integer> {
    Supplier findFirstByOrderByIdDesc();

    Supplier findByIdAndItemSupplierStatus(Integer supplierId, ItemSupplierStatus itemSupplierStatus);
}
