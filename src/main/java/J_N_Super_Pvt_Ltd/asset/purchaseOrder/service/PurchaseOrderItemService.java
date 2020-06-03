package lk.J_N_Super_Pvt_Ltd.asset.purchaseOrder.service;


import lk.J_N_Super_Pvt_Ltd.asset.purchaseOrder.dao.PurchaseOrderItemDao;
import lk.J_N_Super_Pvt_Ltd.asset.purchaseOrder.entity.PurchaseOrderItem;
import lk.J_N_Super_Pvt_Ltd.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@CacheConfig(cacheNames = "purchaseOrderItem")
public class PurchaseOrderItemService implements AbstractService<PurchaseOrderItem, Integer> {
    private final PurchaseOrderItemDao purchaseOrderDao;

    @Autowired
    public PurchaseOrderItemService(PurchaseOrderItemDao purchaseOrderDao) {
        this.purchaseOrderDao = purchaseOrderDao;
    }

    public List<PurchaseOrderItem> findAll() {
        return purchaseOrderDao.findAll();
    }

    public PurchaseOrderItem findById(Integer id) {
        return purchaseOrderDao.getOne(id);
    }

    public PurchaseOrderItem persist(PurchaseOrderItem purchaseOrderItem) {
        return purchaseOrderDao.save(purchaseOrderItem);
    }

    public boolean delete(Integer id) {
        purchaseOrderDao.deleteById(id);
        return false;
    }

    public List<PurchaseOrderItem> search(PurchaseOrderItem purchaseOrderItem) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<PurchaseOrderItem> purchaseRequestExample = Example.of(purchaseOrderItem, matcher);
        return purchaseOrderDao.findAll(purchaseRequestExample);
    }
}
