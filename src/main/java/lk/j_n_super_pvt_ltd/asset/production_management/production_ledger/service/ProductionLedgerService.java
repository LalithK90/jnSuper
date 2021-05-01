package lk.j_n_super_pvt_ltd.asset.production_management.production_ledger.service;

import lk.j_n_super_pvt_ltd.asset.production_management.production_ledger.dao.ProductionLedgerDao;
import lk.j_n_super_pvt_ltd.asset.production_management.production_ledger.entity.ProductionLedger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductionLedgerService {
private final ProductionLedgerDao productionLedgerDao;

  public ProductionLedgerService(ProductionLedgerDao productionLedgerDao) {
    this.productionLedgerDao = productionLedgerDao;
  }

  public List< ProductionLedger > findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to) {
  return productionLedgerDao.findByCreatedAtIsBetween(from, to);
  }
}
