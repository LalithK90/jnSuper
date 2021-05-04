package lk.j_n_super_pvt_ltd.asset.production_management.production.controller;


import lk.j_n_super_pvt_ltd.asset.invoice.entity.enums.InvoiceValidOrNot;
import lk.j_n_super_pvt_ltd.asset.item.entity.enums.ProductionRetail;
import lk.j_n_super_pvt_ltd.asset.item.service.ItemService;
import lk.j_n_super_pvt_ltd.asset.ledger.controller.LedgerController;
import lk.j_n_super_pvt_ltd.asset.ledger.entity.Ledger;
import lk.j_n_super_pvt_ltd.asset.ledger.service.LedgerService;
import lk.j_n_super_pvt_ltd.asset.production_management.production.entity.Production;
import lk.j_n_super_pvt_ltd.asset.production_management.production.entity.enums.ProductionStatus;
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
@RequestMapping( "/production" )
public class ProductionController {
  private final ProductionService productionService;
  private final ProductionItemService productionItemService;
  private final ProductionLedgerService productionLedgerService;
  private final ItemService itemService;
  private final LedgerService ledgerService;
  private final DateTimeAgeService dateTimeAgeService;
  private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

  public ProductionController(ProductionService productionService, ProductionItemService productionItemService,
                              ProductionLedgerService productionLedgerService, ItemService itemService,
                              LedgerService ledgerService, DateTimeAgeService dateTimeAgeService,
                              MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
    this.productionService = productionService;
    this.productionItemService = productionItemService;
    this.productionLedgerService = productionLedgerService;
    this.itemService = itemService;
    this.ledgerService = ledgerService;
    this.dateTimeAgeService = dateTimeAgeService;
    this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
  }


  private String commonProduction(Model model, List< Production > productions, String searchUrl) {
    model.addAttribute("productions", productions);
    model.addAttribute("firstInvoiceMessage", true);
    model.addAttribute("searchUrl", searchUrl);
    return "production/production";
  }

  @GetMapping
  public String production(Model model) {
    List< Production > productions =
        productionService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(dateTimeAgeService.getPastDateByMonth(3)), dateTimeAgeService.dateTimeToLocalDateEndInDay(LocalDate.now()));
    String searchUrl = "/production/search";
    return commonProduction(model, productions, searchUrl);
  }

  @GetMapping( "/search" )
  public String invoiceSearch(@RequestAttribute( "startDate" ) LocalDate startDate,
                              @RequestAttribute( "endDate" ) LocalDate endDate, Model model) {
    List< Production > productions =
        productionService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate),
                                                   dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate)).stream().filter(z -> z.getProductionStatus().equals(ProductionStatus.INI)).collect(Collectors.toList());
    String searchUrl = "/production/search";
    return commonProduction(model, productions, searchUrl);
  }

  @GetMapping( "/initial" )
  public String productionInitial(Model model) {
    List< Production > productions =
        productionService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(dateTimeAgeService.getPastDateByMonth(3)), dateTimeAgeService.dateTimeToLocalDateEndInDay(LocalDate.now())).stream().filter(z -> z.getProductionStatus().equals(ProductionStatus.INI)).collect(Collectors.toList());
    String searchUrl = "/production/initial/search";
    return commonProduction(model, productions, searchUrl);
  }

  @GetMapping( "/initial/search" )
  public String invoiceInitialSearch(@RequestAttribute( "startDate" ) LocalDate startDate,
                                     @RequestAttribute( "endDate" ) LocalDate endDate, Model model) {
    List< Production > productions =
        productionService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate),
                                                   dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate)).stream().filter(z -> z.getProductionStatus().equals(ProductionStatus.INI)).collect(Collectors.toList());
    String searchUrl = "/production/initial/search";
    return commonProduction(model, productions, searchUrl);
  }

  @GetMapping( "/pending" )
  public String productionPending(Model model) {
    List< Production > productions =
        productionService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(dateTimeAgeService.getPastDateByMonth(3)), dateTimeAgeService.dateTimeToLocalDateEndInDay(LocalDate.now())).stream().filter(z -> z.getProductionStatus().equals(ProductionStatus.PENDING)).collect(Collectors.toList());
    String searchUrl = "/production/pending/search";
    return commonProduction(model, productions, searchUrl);
  }

  @GetMapping( "/pending/search" )
  public String invoicePendingSearch(@RequestAttribute( "startDate" ) LocalDate startDate,
                                     @RequestAttribute( "endDate" ) LocalDate endDate, Model model) {
    List< Production > productions =
        productionService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate),
                                                   dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate)).stream().filter(z -> z.getProductionStatus().equals(ProductionStatus.PENDING)).collect(Collectors.toList());
    String searchUrl = "/production/pending/search";
    return commonProduction(model, productions, searchUrl);
  }

  @GetMapping( "/completed" )
  public String productionCompleted(Model model) {
    List< Production > productions =
        productionService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(dateTimeAgeService.getPastDateByMonth(3)), dateTimeAgeService.dateTimeToLocalDateEndInDay(LocalDate.now())).stream().filter(z -> z.getProductionStatus().equals(ProductionStatus.COMPLETED)).collect(Collectors.toList());
    String searchUrl = "/production/completed/search";
    return commonProduction(model, productions, searchUrl);
  }

  @GetMapping( "/completed/search" )
  public String invoiceCompletedSearch(@RequestAttribute( "startDate" ) LocalDate startDate,
                                       @RequestAttribute( "endDate" ) LocalDate endDate, Model model) {
    List< Production > productions =
        productionService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate),
                                                   dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate)).stream().filter(z -> z.getProductionStatus().equals(ProductionStatus.COMPLETED)).collect(Collectors.toList());
    String searchUrl = "/production/completed/search";
    return commonProduction(model, productions, searchUrl);
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
   bindingResult.getAllErrors().forEach(System.out::println);
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

    production.getProductionLedgers().forEach(x -> {
      x.setProduction(production);
      productionLedgers.add(x);
    });
    production.setProductionLedgers(productionLedgers);
    Production saveInvoice = productionService.persist(production);

    return "redirect:/production";
  }


  @GetMapping( "/remove/{id}" )
  public String removeInvoice(@PathVariable( "id" ) Integer id) {
    Production production = productionService.findById(id);
    production.setInvoiceValidOrNot(InvoiceValidOrNot.NOTVALID);
    productionService.persist(production);
    return "redirect:/production";
  }


}
