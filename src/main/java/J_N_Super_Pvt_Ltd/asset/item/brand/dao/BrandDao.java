package J_N_Super_Pvt_Ltd.asset.item.brand.dao;


import J_N_Super_Pvt_Ltd.asset.item.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {
}