package lk.j_n_super_pvt_ltd.asset.production_management.production.dao;


import lk.j_n_super_pvt_ltd.asset.production_management.production.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

;

public interface ProductionDao extends JpaRepository< Production, Integer > {

  Production findFirstByOrderByIdDesc();

  List< Production > findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to);

  List< Production > findByCreatedAtIsBetweenAndCreatedBy(LocalDateTime from, LocalDateTime to, String userName);
}
