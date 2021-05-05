package lk.j_n_super_pvt_ltd.asset.item.dao;


import lk.j_n_super_pvt_ltd.asset.item.entity.Item;
import lk.j_n_super_pvt_ltd.asset.item.entity.enums.ProductionRetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao extends JpaRepository<Item, Integer> {
    Item findFirstByOrderByIdDesc();

  List< Item> findByProductionRetail(ProductionRetail productionRetail);
}
