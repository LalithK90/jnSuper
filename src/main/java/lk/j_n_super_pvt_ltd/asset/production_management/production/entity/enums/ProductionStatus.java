package lk.j_n_super_pvt_ltd.asset.production_management.production.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductionStatus {
  INI("Initial"),
  COMPLETED("Process Completed"),
  PENDING("Pending");
  private final String productionStatus;
}
