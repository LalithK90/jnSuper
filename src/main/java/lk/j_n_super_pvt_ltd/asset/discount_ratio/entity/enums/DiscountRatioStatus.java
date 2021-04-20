package lk.j_n_super_pvt_ltd.asset.discount_ratio.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountRatioStatus {
  ACTIVE("Active"),
  INACTIVE("Inactive"),
  TEMPHOLD("Temporally Hold"),
  EX("Expired");

  private final String discountRatioStatus;
}
