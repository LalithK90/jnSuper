package lk.J_N_Super_Pvt_Ltd.asset.item.category.dao;


import lk.J_N_Super_Pvt_Ltd.asset.item.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
}
