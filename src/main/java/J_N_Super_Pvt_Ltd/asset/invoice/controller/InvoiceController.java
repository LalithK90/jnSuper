package J_N_Super_Pvt_Ltd.asset.invoice.controller;

import J_N_Super_Pvt_Ltd.asset.customer.service.CustomerService;
import J_N_Super_Pvt_Ltd.asset.invoice.entity.Invoice;
import J_N_Super_Pvt_Ltd.asset.invoice.service.InvoiceService;
import J_N_Super_Pvt_Ltd.asset.item.service.ItemService;
import J_N_Super_Pvt_Ltd.asset.ledger.service.LedgerService;
import J_N_Super_Pvt_Ltd.util.service.DateTimeAgeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalTime;

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
                model.addAttribute("invoices", invoiceService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(dateTimeAgeService.getPastDateByMonth(3)),dateTimeAgeService.dateTimeToLocalDateEndInDay(LocalDate.now())));
        return "invoice/invoice";
    }


    @GetMapping( "/add" )
    public String getInvoiceForm(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "invoice/makeInvoice";
    }


    @PostMapping
    public String persistInvoice() {
        return "redirect:/invoice";
    }

}
