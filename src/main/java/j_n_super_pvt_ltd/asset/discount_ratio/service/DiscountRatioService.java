package j_n_super_pvt_ltd.asset.discount_ratio.service;


import j_n_super_pvt_ltd.asset.discount_ratio.dao.DiscountRatioDao;
import j_n_super_pvt_ltd.asset.discount_ratio.entity.DiscountRatio;
import j_n_super_pvt_ltd.util.interfaces.AbstractService;
import j_n_super_pvt_ltd.asset.common_asset.model.enums.LiveOrDead;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountRatioService implements AbstractService<DiscountRatio, Integer > {
private final DiscountRatioDao discountRatioDao;

    public DiscountRatioService(DiscountRatioDao discountRatioDao) {
        this.discountRatioDao = discountRatioDao;
    }

    public List< DiscountRatio > findAll() {
        return discountRatioDao.findAll();
    }

    public DiscountRatio findById(Integer id) {
        return discountRatioDao.getOne(id);
    }

    public DiscountRatio persist(DiscountRatio discountRatio) {
        if ( discountRatio.getId() == null ){
            discountRatio.setLiveOrDead(LiveOrDead.ACTIVE);
        }
        return discountRatioDao.save(discountRatio);
    }

    public boolean delete(Integer id) {
        DiscountRatio discountRatio =  discountRatioDao.getOne(id);
        discountRatio.setLiveOrDead(LiveOrDead.STOP);
        discountRatioDao.save(discountRatio);
        return false;
    }

    public List< DiscountRatio > search(DiscountRatio discountRatio) {
        return null;
    }
}
