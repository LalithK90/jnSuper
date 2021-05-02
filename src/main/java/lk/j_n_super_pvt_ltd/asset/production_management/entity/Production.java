package lk.j_n_super_pvt_ltd.asset.production_management.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.j_n_super_pvt_ltd.asset.production_management.entity.enums.ProductionStatus;
import lk.j_n_super_pvt_ltd.asset.production_management.production_item.entity.ProductionItem;
import lk.j_n_super_pvt_ltd.asset.production_management.production_ledger.entity.ProductionLedger;
import lk.j_n_super_pvt_ltd.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Production")
public class Production extends AuditEntity {

  @Enumerated( EnumType.STRING)
  private ProductionStatus productionStatus;

  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "production")
  private List< ProductionLedger > productionLedgers;

  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "production")
  private List< ProductionItem > productionItems;
}
