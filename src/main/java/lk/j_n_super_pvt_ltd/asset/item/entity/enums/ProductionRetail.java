package lk.j_n_super_pvt_ltd.asset.item.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductionRetail {
  PRODUCTION("Production Item"),
  BULK("Bulk Item"),
  RETAIL("Retail Item");

  private final String productionRetail;
}
