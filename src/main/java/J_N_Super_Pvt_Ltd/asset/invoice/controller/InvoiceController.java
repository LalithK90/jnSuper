package J_N_Super_Pvt_Ltd.asset.invoice.controller;

import J_N_Super_Pvt_Ltd.asset.customer.service.CustomerService;
import J_N_Super_Pvt_Ltd.asset.invoice.service.InvoiceService;
import J_N_Super_Pvt_Ltd.asset.item.service.ItemService;
import J_N_Super_Pvt_Ltd.asset.ledger.service.LedgerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/invoice" )
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final ItemService itemService;
    private final CustomerService customerService;
    private final LedgerService ledgerService;

    public InvoiceController(InvoiceService invoiceService, ItemService itemService, CustomerService customerService,
                             LedgerService ledgerService) {
        this.invoiceService = invoiceService;
        this.itemService = itemService;
        this.customerService = customerService;
        this.ledgerService = ledgerService;
    }


}
