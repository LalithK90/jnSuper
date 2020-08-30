package j_n_super_pvt_ltd.asset.item.dao;


import j_n_super_pvt_ltd.asset.category.entity.Category;
import j_n_super_pvt_ltd.asset.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao extends JpaRepository<Item, Integer> {
    Item findFirstByOrderByIdDesc();

    List<Item> findByCategoryOrderByIdDesc(Category category);
}
