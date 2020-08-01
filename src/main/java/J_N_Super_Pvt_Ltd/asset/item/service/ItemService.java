package J_N_Super_Pvt_Ltd.asset.item.service;


import J_N_Super_Pvt_Ltd.asset.item.dao.ItemDao;
import J_N_Super_Pvt_Ltd.asset.item.entity.Enum.ItemStatus;
import J_N_Super_Pvt_Ltd.asset.item.entity.Item;
import J_N_Super_Pvt_Ltd.asset.ledger.dao.LedgerDao;
import J_N_Super_Pvt_Ltd.asset.ledger.entity.Ledger;
import J_N_Super_Pvt_Ltd.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "item")
public class ItemService implements AbstractService<Item, Integer> {
    private final ItemDao itemDao;
    private final LedgerDao ledgerDao;

    @Autowired
    public ItemService(ItemDao itemDao, LedgerDao ledgerDao) {
        this.itemDao = itemDao;
        this.ledgerDao = ledgerDao;
    }

    public List<Item> findAll() {
        return itemDao.findAll();
    }

    public Item findById(Integer id) {
        return itemDao.getOne(id);
    }

    private String makeItemCode(String lastNumber){
        String newNumber = "";
        if (lastNumber !=null) {
            int number = Integer.parseInt(lastNumber);

            if (number<10){
                newNumber = "00"+String.valueOf(number+1);
            }
            if (10 < number && number < 100){
newNumber = "0"+String.valueOf(number+1);
            }
            if (100<number){
                newNumber = String.valueOf(number+1);
            }
            return newNumber;
        }else {
            return  "0001";

        }
    }

    public Item persist(Item item) {
        if (item.getId() == null) {
            //need to create code to item
            makeNewItemWithItemCode(item);
        }
        //if item buying price was changed (increase/decrease) by supplier,
        // need to change that item as supplier not currently buying and save as new supplier_item
        if (item.getId() != null) {
            Ledger itemLedger = ledgerDao.findByItem(item);
            if (!item.getSellPrice().equals(itemLedger.getSellPrice())) {
                //need to create code to item
                makeNewItemWithItemCode(item);
            }
        }
        return itemDao.save(item);
    }

    private void makeNewItemWithItemCode(Item item) {
        String code =item.getCategory().getMainCategory()
                + item.getCategory().getName().trim().substring(0,2)
                + item.getName().trim().substring(0,2);
        item.setItemStatus(ItemStatus.NOT_AVAILABLE);
        item.setCode(code +makeItemCode(null));
        List< Ledger > ledgers = item.getLedgers();
        Ledger ledger = new Ledger();
        ledger.setQuantity("0");
        ledger.setItem(item);
        ledger.setSellPrice(item.getSellPrice());
        ledgers.add(ledger);
        item.setLedgers(ledgers);
    }

    public boolean delete(Integer id) {
        itemDao.deleteById(id);
        return false;
    }

    public List<Item> search(Item item) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Item> itemExample = Example.of(item, matcher);
        return itemDao.findAll(itemExample);
    }

    public Item lastItem() {
        return itemDao.findFirstByOrderByIdDesc();
    }
}