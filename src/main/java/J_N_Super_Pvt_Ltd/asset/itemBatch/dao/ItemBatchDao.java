package J_N_Super_Pvt_Ltd.asset.itemBatch.dao;


import J_N_Super_Pvt_Ltd.asset.item.entity.Item;
import J_N_Super_Pvt_Ltd.asset.itemBatch.entity.ItemBatch;
import J_N_Super_Pvt_Ltd.asset.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemBatchDao extends JpaRepository<ItemBatch, Integer> {
    ItemBatch findFirstByOrderByIdDesc();


    ItemBatch findByItemAndSupplier(Item item, Supplier supplier);
}
