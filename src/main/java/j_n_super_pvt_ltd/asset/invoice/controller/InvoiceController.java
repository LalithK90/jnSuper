package j_n_super_pvt_ltd.asset.invoice.controller;

import j_n_super_pvt_ltd.asset.customer.service.CustomerService;
import j_n_super_pvt_ltd.asset.discountRatio.service.DiscountRatioService;
import j_n_super_pvt_ltd.asset.invoice.entity.Enum.InvoicePrintOrNot;
import j_n_super_pvt_ltd.asset.invoice.entity.Enum.InvoiceValidOrNot;
import j_n_super_pvt_ltd.asset.invoice.entity.Enum.PaymentMethod;
import j_n_super_pvt_ltd.asset.invoice.entity.Invoice;
import j_n_super_pvt_ltd.asset.invoice.service.InvoiceService;
import j_n_super_pvt_ltd.asset.item.service.ItemService;
import j_n_super_pvt_ltd.asset.ledger.service.LedgerService;
import j_n_super_pvt_ltd.util.service.DateTimeAgeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping( "/invoice" )
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final ItemService itemService;
    private final CustomerService customerService;
    private final LedgerService ledgerService;
    private final DateTimeAgeService dateTimeAgeService;
    private final DiscountRatioService discountRatioService;

    public InvoiceController(InvoiceService invoiceService, ItemService itemService, CustomerService customerService,
                             LedgerService ledgerService, DateTimeAgeService dateTimeAgeService,
                             DiscountRatioService discountRatioService) {
        this.invoiceService = invoiceService;
        this.itemService = itemService;
        this.customerService = customerService;
        this.ledgerService = ledgerService;
        this.dateTimeAgeService = dateTimeAgeService;
        this.discountRatioService = discountRatioService;
    }

    @GetMapping
    public String invoice(Model model) {
        model.addAttribute("invoices",
                           invoiceService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(dateTimeAgeService.getPastDateByMonth(3)), dateTimeAgeService.dateTimeToLocalDateEndInDay(LocalDate.now())));
        model.addAttribute("firstInvoiceMessage", true);
        return "invoice/invoice";
    }

    @GetMapping( "/search" )
    public String invoiceSearch(@RequestAttribute( "startDate" ) LocalDate startDate,
                                @RequestAttribute( "endDate" ) LocalDate endDate, Model model) {
        model.addAttribute("invoices",
                           invoiceService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate), dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate)));
        model.addAttribute("firstInvoiceMessage", true);
        return "invoice/invoice";
    }

    private String common(Model model, Invoice invoice) {
        model.addAttribute("invoice", invoice);
        model.addAttribute("invoicePrintOrNots", InvoicePrintOrNot.values());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        model.addAttribute("invoiceValidOrNots", InvoiceValidOrNot.values());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("discountRatios", discountRatioService.findAll());
        model.addAttribute("ledgers", ledgerService.findAll());

        return "invoice/makeInvoice";
    }

    @GetMapping( "/add" )
    public String getInvoiceForm(Model model) {
        return common(model, new Invoice());
    }

    @GetMapping( "/{id}" )
    public String viewDetails(@PathVariable Integer id, Model model) {
        model.addAttribute("invoiceDetail", invoiceService.findById(id));
        return "invoice/invoice-detail";
    }

    @PostMapping
    public String persistInvoice(@Valid @ModelAttribute Invoice invoice, BindingResult bindingResult, Model model) {
        if ( bindingResult.hasErrors() ) {
            return common(model, invoice);
        }
//todo ->
        Invoice lastDB = invoiceService.findByLastInvoice();
        /*if ( lastDB != null ) {
            invoice.setCode();
        } else {

        }*/

        invoice.setInvoiceValidOrNot(InvoiceValidOrNot.VALID);
        return "redirect:/invoice";
    }


    @GetMapping( "/remove/{id}" )
    public String removeInvoice(@PathVariable( "id" ) Integer id) {
        Invoice invoice = invoiceService.findById(id);
        invoice.setInvoiceValidOrNot(InvoiceValidOrNot.NOTVALID);
        invoiceService.persist(invoice);
        return "redirect:/invoice";
    }
}
