package J_N_Super_Pvt_Ltd.asset.itemBatch.dao;


import J_N_Super_Pvt_Ltd.asset.itemBatch.entity.ItemBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemBatchDao extends JpaRepository<ItemBatch, Integer> {
    ItemBatch findFirstByOrderByIdDesc();


}
