package J_N_Super_Pvt_Ltd.asset.discountRatio.dao;


import J_N_Super_Pvt_Ltd.asset.discountRatio.entity.DiscountRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRatioDao extends JpaRepository< DiscountRatio, Integer > {
}
