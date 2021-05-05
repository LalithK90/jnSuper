package lk.j_n_super_pvt_ltd.asset.production_management.production_item.service;

import lk.j_n_super_pvt_ltd.asset.production_management.production.entity.Production;
import lk.j_n_super_pvt_ltd.asset.production_management.production_item.dao.ProductionItemDao;
import lk.j_n_super_pvt_ltd.asset.production_management.production_item.entity.ProductionItem;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductionItemService {
private final ProductionItemDao productionItemDao;

  public ProductionItemService(ProductionItemDao productionItemDao) {
    this.productionItemDao = productionItemDao;
  }

  public List< ProductionItem > findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to) {
  return productionItemDao.findByCreatedAtIsBetween(from, to);
  }

  public ProductionItem persist(ProductionItem productionItem) {
    return productionItemDao.save(productionItem);
  }
}
