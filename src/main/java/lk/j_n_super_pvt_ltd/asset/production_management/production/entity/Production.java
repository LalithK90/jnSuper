package lk.j_n_super_pvt_ltd.asset.production_management.production.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.j_n_super_pvt_ltd.asset.invoice.entity.enums.InvoiceValidOrNot;
import lk.j_n_super_pvt_ltd.asset.ledger.entity.Ledger;
import lk.j_n_super_pvt_ltd.asset.production_management.production.entity.enums.ProductionStatus;
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

  @Column(unique = true)
  private String code;

  @Enumerated(EnumType.STRING)
  private InvoiceValidOrNot invoiceValidOrNot;

  @Enumerated( EnumType.STRING)
  private ProductionStatus productionStatus;

  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "production")
  private List< ProductionLedger > productionLedgers;

  @OneToMany(mappedBy = "production")
  private List< ProductionItem > productionItems;

  @OneToMany(mappedBy = "production")
  private List< Ledger > ledgers;
}
