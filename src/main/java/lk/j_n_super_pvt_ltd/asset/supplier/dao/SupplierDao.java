package lk.j_n_super_pvt_ltd.asset.supplier.dao;

import lk.j_n_super_pvt_ltd.asset.supplier.entity.Supplier;
import lk.j_n_super_pvt_ltd.asset.supplier_item.entity.enums.ItemSupplierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDao extends JpaRepository<Supplier, Integer> {
    Supplier findFirstByOrderByIdDesc();

    Supplier findByIdAndItemSupplierStatus(Integer supplierId, ItemSupplierStatus itemSupplierStatus);

    Supplier findByName(String name);
    Supplier findByEmail(String email);

}
