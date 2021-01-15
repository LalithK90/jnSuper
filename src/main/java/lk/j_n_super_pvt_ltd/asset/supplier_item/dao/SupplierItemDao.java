package lk.j_n_super_pvt_ltd.asset.supplier_item.dao;


import lk.j_n_super_pvt_ltd.asset.item.entity.Item;
import lk.j_n_super_pvt_ltd.asset.supplier.entity.Supplier;
import lk.j_n_super_pvt_ltd.asset.supplier_item.entity.enums.ItemSupplierStatus;
import lk.j_n_super_pvt_ltd.asset.supplier_item.entity.SupplierItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierItemDao extends JpaRepository< SupplierItem, Integer> {
    SupplierItem findBySupplierAndItem(Supplier supplier, Item item);

    List<SupplierItem> findBySupplier(Supplier supplier);

    List<SupplierItem> findBySupplierAndItemSupplierStatus(Supplier supplier, ItemSupplierStatus itemSupplierStatus);

    List<SupplierItem> findByItem(Item item);

    Item findByItemAndSupplier(Item item, Supplier supplier);

    SupplierItem findBySupplierAndItemAndItemSupplierStatus(Supplier supplier, Item item, ItemSupplierStatus itemSupplierStatus);
}
