package lk.j_n_super_pvt_ltd.asset.production_management.production.controller;


import lk.j_n_super_pvt_ltd.asset.invoice.entity.enums.InvoiceValidOrNot;
import lk.j_n_super_pvt_ltd.asset.item.entity.enums.ProductionRetail;
import lk.j_n_super_pvt_ltd.asset.item.service.ItemService;
import lk.j_n_super_pvt_ltd.asset.ledger.controller.LedgerController;
import lk.j_n_super_pvt_ltd.asset.ledger.entity.Ledger;
import lk.j_n_super_pvt_ltd.asset.ledger.service.LedgerService;
import lk.j_n_super_pvt_ltd.asset.production_management.production.entity.Production;
import lk.j_n_super_pvt_ltd.asset.production_management.production.service.ProductionService;
import lk.j_n_super_pvt_ltd.asset.production_management.production_item.service.ProductionItemService;
import lk.j_n_super_pvt_ltd.asset.production_management.production_ledger.entity.ProductionLedger;
import lk.j_n_super_pvt_ltd.asset.production_management.production_ledger.service.ProductionLedgerService;
import lk.j_n_super_pvt_ltd.util.service.DateTimeAgeService;
import lk.j_n_super_pvt_ltd.util.service.MakeAutoGenerateNumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/production")
public class ProductionController {
  private final ProductionService productionService;
  private final ProductionItemService productionItemService;
  private final ProductionLedgerService productionLedgerService;
  private final ItemService itemService;
  private final LedgerService ledgerService;
  private final DateTimeAgeService dateTimeAgeService;
  private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

  public ProductionController(ProductionService productionService, ProductionItemService productionItemService,
                              ProductionLedgerService productionLedgerService, ItemService itemService, LedgerService ledgerService, DateTimeAgeService dateTimeAgeService, MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
    this.productionService = productionService;
    this.productionItemService = productionItemService;
    this.productionLedgerService = productionLedgerService;
    this.itemService = itemService;
    this.ledgerService = ledgerService;
    this.dateTimeAgeService = dateTimeAgeService;
    this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
  }

  @GetMapping
  public String production(Model model) {
    model.addAttribute("productions",
                       productionService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(dateTimeAgeService.getPastDateByMonth(3)), dateTimeAgeService.dateTimeToLocalDateEndInDay(LocalDate.now())));
    model.addAttribute("firstInvoiceMessage", true);
    return "production/production";
  }

  @GetMapping( "/search" )
  public String invoiceSearch(@RequestAttribute( "startDate" ) LocalDate startDate,
                              @RequestAttribute( "endDate" ) LocalDate endDate, Model model) {
    model.addAttribute("productions",
                       productionService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate), dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate)));
    model.addAttribute("firstInvoiceMessage", true);
    return "production/production";
  }

  private String common(Model model, Production production) {
    model.addAttribute("production", production);
    model.addAttribute("ledgerItemURL", MvcUriComponentsBuilder
        .fromMethodName(LedgerController.class, "findId", "")
        .build()
        .toString());
    //send not expired and not zero quantity
    model.addAttribute("ledgers", ledgerService.findAll()
        .stream()
        .filter(x -> 0 < Integer.parseInt(x.getQuantity()) && x.getExpiredDate().isAfter(LocalDate.now()) && itemService.findById(x.getItem().getId()).getProductionRetail().equals(ProductionRetail.PRODUCTION))
        .collect(Collectors.toList()));
    return "production/addProduction";

  }

  @GetMapping( "/add" )
  public String getInvoiceForm(Model model) {
    return common(model, new Production());
  }

  @GetMapping( "/{id}" )
  public String viewDetails(@PathVariable Integer id, Model model) {
    Production production = productionService.findById(id);
    model.addAttribute("productionDetail", production);
    return "production/production-detail";
  }

  @PostMapping
  public String persistInvoice(@Valid @ModelAttribute Production production, BindingResult bindingResult, Model model) {
    if ( bindingResult.hasErrors() ) {
      return common(model, production);
    }
    if ( production.getId() == null ) {
      if ( productionService.findByLastInvoice() == null ) {
        //need to generate new one
        production.setCode("JNSP" + makeAutoGenerateNumberService.numberAutoGen(null).toString());
      } else {

        //if there is customer in db need to get that customer's code and increase its value
        String previousCode = productionService.findByLastInvoice().getCode().substring(4);
        production.setCode("JNSP" + makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
      }
    }

    List< ProductionLedger > productionLedgers = new ArrayList<>();

    production.getProductionLedgers().forEach(x-> {
      x.setProduction(production);
      productionLedgers.add(x);
    });
    production.setProductionLedgers(productionLedgers);
    Production saveInvoice = productionService.persist(production);

    //todo here
    return "redirect:/production/fileView/"+saveInvoice.getId();
  }


  @GetMapping( "/remove/{id}" )
  public String removeInvoice(@PathVariable( "id" ) Integer id) {
    Production production = productionService.findById(id);
    production.setInvoiceValidOrNot(InvoiceValidOrNot.NOTVALID);
    productionService.persist(production);
    return "redirect:/production";
  }



}
