package lk.j_n_super_pvt_ltd.asset.production_management.production_ledger.dao;

import lk.j_n_super_pvt_ltd.asset.production_management.production_ledger.entity.ProductionLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductionLedgerDao extends JpaRepository< ProductionLedger, Integer > {
  List< ProductionLedger > findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to);
}
