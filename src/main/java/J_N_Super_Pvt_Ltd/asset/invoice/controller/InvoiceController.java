package J_N_Super_Pvt_Ltd.asset.invoice.controller;

import J_N_Super_Pvt_Ltd.asset.customer.service.CustomerService;
import J_N_Super_Pvt_Ltd.asset.invoice.entity.Enum.InvoiceValidOrNot;
import J_N_Super_Pvt_Ltd.asset.invoice.entity.Invoice;
import J_N_Super_Pvt_Ltd.asset.invoice.service.InvoiceService;
import J_N_Super_Pvt_Ltd.asset.item.service.ItemService;
import J_N_Super_Pvt_Ltd.asset.ledger.service.LedgerService;
import J_N_Super_Pvt_Ltd.util.service.DateTimeAgeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping( "/invoice" )
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final ItemService itemService;
    private final CustomerService customerService;
    private final LedgerService ledgerService;
    private final DateTimeAgeService dateTimeAgeService;

    public InvoiceController(InvoiceService invoiceService, ItemService itemService, CustomerService customerService,
                             LedgerService ledgerService, DateTimeAgeService dateTimeAgeService) {
        this.invoiceService = invoiceService;
        this.itemService = itemService;
        this.customerService = customerService;
        this.ledgerService = ledgerService;
        this.dateTimeAgeService = dateTimeAgeService;
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

    @GetMapping( "/add" )
    public String getInvoiceForm(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "invoice/makeInvoice";
    }

    @GetMapping( "/{id}" )
    public String viewDetails(@PathVariable Integer id) {
        return "invoice/invoice-detail";
    }

    @PostMapping
    public String persistInvoice() {
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
