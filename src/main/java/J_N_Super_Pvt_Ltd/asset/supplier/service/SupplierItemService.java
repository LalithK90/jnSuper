package J_N_Super_Pvt_Ltd.asset.supplier.service;


import J_N_Super_Pvt_Ltd.asset.item.entity.Item;
import J_N_Super_Pvt_Ltd.asset.supplier.dao.SupplierItemDao;
import J_N_Super_Pvt_Ltd.asset.supplier.entity.Enum.ItemSupplierStatus;
import J_N_Super_Pvt_Ltd.asset.supplier.entity.Supplier;
import J_N_Super_Pvt_Ltd.asset.supplier.entity.SupplierItem;
import J_N_Super_Pvt_Ltd.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "supplierItem")
public class SupplierItemService implements AbstractService<SupplierItem, Integer> {
    private final SupplierItemDao supplierItemDao;

    @Autowired
    public SupplierItemService(SupplierItemDao supplierItemDao) {
        this.supplierItemDao = supplierItemDao;
    }

    public List<SupplierItem> findAll() {
        return supplierItemDao.findAll();
    }

    public SupplierItem findById(Integer id) {
        return supplierItemDao.getOne(id);
    }

    public SupplierItem persist(SupplierItem supplierItem) {
        if (supplierItem.getId()==null){
            supplierItem.setItemSupplierStatus(ItemSupplierStatus.CURRENTLY_BUYING);
        }
        return supplierItemDao.save(supplierItem);
    }

    public boolean delete(Integer id) {
        supplierItemDao.deleteById(id);
        return false;
    }

    public List<SupplierItem> search(SupplierItem supplierItem) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<SupplierItem> supplierItemExample = Example.of(supplierItem, matcher);
        return supplierItemDao.findAll(supplierItemExample);
    }

    public SupplierItem findBySupplierAndItem(Supplier supplier, Item item) {
        return supplierItemDao.findBySupplierAndItem(supplier, item);
    }

    public List<SupplierItem> findBySupplier(Supplier supplier) {
        return supplierItemDao.findBySupplier(supplier);
    }

    public List<SupplierItem> findBySupplierAndItemSupplierStatus(Supplier supplier, ItemSupplierStatus itemSupplierStatus) {
  return supplierItemDao.findBySupplierAndItemSupplierStatus(supplier,itemSupplierStatus);
    }
}
