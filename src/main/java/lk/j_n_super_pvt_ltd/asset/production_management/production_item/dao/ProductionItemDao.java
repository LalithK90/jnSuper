package lk.j_n_super_pvt_ltd.asset.production_management.production_item.dao;

import lk.j_n_super_pvt_ltd.asset.production_management.production_item.entity.ProductionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductionItemDao extends JpaRepository< ProductionItem, Integer > {
  List< ProductionItem > findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to);
}
