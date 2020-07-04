package J_N_Super_Pvt_Ltd.asset.PurchaseOrder.service;



import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.dao.PurchaseOrderSupplierDao;
import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.PurchaseOrderSupplier;
import J_N_Super_Pvt_Ltd.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
@CacheConfig(cacheNames = "purchaseOrderSupplier")
public class PurchaseOrderSupplierService implements AbstractService<PurchaseOrderSupplier, Integer> {
    private final PurchaseOrderSupplierDao purchaseOrderDao;

    @Autowired
    public PurchaseOrderSupplierService(PurchaseOrderSupplierDao purchaseOrderDao) {
        this.purchaseOrderDao = purchaseOrderDao;
    }

    public List<PurchaseOrderSupplier> findAll() {
        return purchaseOrderDao.findAll();
    }

    public PurchaseOrderSupplier findById(Integer id) {
        return purchaseOrderDao.getOne(id);
    }

    public PurchaseOrderSupplier persist(PurchaseOrderSupplier purchaseOrderSupplier) {
        return purchaseOrderDao.save(purchaseOrderSupplier);
    }

    public boolean delete(Integer id) {
        purchaseOrderDao.deleteById(id);
        return false;
    }

    public List<PurchaseOrderSupplier> search(PurchaseOrderSupplier purchaseOrderSupplier) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<PurchaseOrderSupplier> purchaseRequestExample = Example.of(purchaseOrderSupplier, matcher);
        return purchaseOrderDao.findAll(purchaseRequestExample);
    }
}
