package lk.j_n_super_pvt_ltd.asset.production_management.controller;

import lk.j_n_super_pvt_ltd.asset.item.service.ItemService;
import lk.j_n_super_pvt_ltd.asset.ledger.service.LedgerService;
import lk.j_n_super_pvt_ltd.asset.production_management.production_item.service.ProductionItemService;
import lk.j_n_super_pvt_ltd.asset.production_management.production_ledger.service.ProductionLedgerService;
import lk.j_n_super_pvt_ltd.asset.production_management.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/production")
public class ProductionController {
  private final ProductionService productionService;
  private final ProductionItemService productionItemService;
  private final ProductionLedgerService productionLedgerService;
  private final ItemService itemService;
  private final LedgerService ledgerService;

  public ProductionController(ProductionService productionService, ProductionItemService productionItemService,
                              ProductionLedgerService productionLedgerService, ItemService itemService, LedgerService ledgerService) {
    this.productionService = productionService;
    this.productionItemService = productionItemService;
    this.productionLedgerService = productionLedgerService;
    this.itemService = itemService;
    this.ledgerService = ledgerService;
  }
  //todo : ->
}
