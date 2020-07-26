package J_N_Super_Pvt_Ltd.asset.itemBatch.service;


import J_N_Super_Pvt_Ltd.asset.item.entity.Enum.ItemStatus;
import J_N_Super_Pvt_Ltd.asset.item.entity.Item;
import J_N_Super_Pvt_Ltd.asset.itemBatch.dao.ItemBatchDao;
import J_N_Super_Pvt_Ltd.asset.itemBatch.entity.ItemBatch;
import J_N_Super_Pvt_Ltd.asset.ledger.entity.Ledger;
import J_N_Super_Pvt_Ltd.asset.ledger.service.LedgerService;
import J_N_Super_Pvt_Ltd.asset.supplier.entity.Supplier;
import J_N_Super_Pvt_Ltd.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "itemBatch")
public class ItemBatchService implements AbstractService<ItemBatch, Integer> {
    private final ItemBatchDao itemBatchDao;
    private final LedgerService ledgerService;

    @Autowired
    public ItemBatchService(ItemBatchDao itemBatchDao, LedgerService ledgerService) {
        this.itemBatchDao = itemBatchDao;
        this.ledgerService = ledgerService;
    }

    public List<ItemBatch> findAll() {
        return itemBatchDao.findAll();
    }

    public ItemBatch findById(Integer id) {
        return itemBatchDao.getOne(id);
    }

    public ItemBatch persist(ItemBatch itemBatch) {
        //if item is new supplier should be save as currently buying item
        if (itemBatch.getId() == null) {
            itemBatch.setItemStatus(ItemStatus.NOT_AVAILABLE);
            ItemBatch savedItemBatch = itemBatchDao.save(itemBatch);
            Ledger ledger = new Ledger();
            ledger.setItemBatch(savedItemBatch);
            ledger.setQuantity("0");
            ledger.setSellPrice(savedItemBatch.getPrice());
            ledgerService.persist(ledger);
            return savedItemBatch;
        }
        //if item buying price was changed (increase/decrease) by supplier,
        // need to change that item as supplier not currently buying and save as new supplier_item
        if (itemBatch.getId() != null) {
            ItemBatch itemBatchDB = itemBatchDao.getOne(itemBatch.getId());
            if (!itemBatch.getPrice().equals(itemBatchDB.getPrice())) {
                //price change item save as new item
                itemBatch.setItemStatus(ItemStatus.NOT_AVAILABLE);
                itemBatch.setId(null);
                //already saved item was change to not currently buying
                itemBatchDB.setItemStatus(ItemStatus.AVAILABLE);
                if (Integer.parseInt(ledgerService.findByItemBatch(itemBatch).getQuantity()) != 0){
                    itemBatch.setItemStatus(ItemStatus.AVAILABLE);
                }else {
                    itemBatch.setItemStatus(ItemStatus.STOP);
                }
            }
        }
        return itemBatchDao.save(itemBatch);
    }

    public boolean delete(Integer id) {
        itemBatchDao.deleteById(id);
        return false;
    }

    public List<ItemBatch> search(ItemBatch itemBatch) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<ItemBatch> itemExample = Example.of(itemBatch, matcher);
        return itemBatchDao.findAll(itemExample);
    }

    public ItemBatch lastItemBatch() {
        return itemBatchDao.findFirstByOrderByIdDesc();
    }

    public ItemBatch findByItemAndSupplier(Item item, Supplier supplier) {
    return itemBatchDao.findByItemAndSupplier(item,supplier);
    }
}