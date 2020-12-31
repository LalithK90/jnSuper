package lk.j_n_super_pvt_ltd.asset.category.dao;

import lk.j_n_super_pvt_ltd.asset.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
}
