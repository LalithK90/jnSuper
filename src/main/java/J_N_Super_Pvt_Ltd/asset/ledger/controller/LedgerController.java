package J_N_Super_Pvt_Ltd.asset.ledger.controller;


import J_N_Super_Pvt_Ltd.asset.ledger.service.LedgerService;
import J_N_Super_Pvt_Ltd.util.service.DateTimeAgeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Controller
@RequestMapping( "/ledger" )
public class LedgerController {
    private final LedgerService ledgerService;
    private final DateTimeAgeService dateTimeAgeService;

    public LedgerController(LedgerService ledgerService, DateTimeAgeService dateTimeAgeService) {
        this.ledgerService = ledgerService;
        this.dateTimeAgeService = dateTimeAgeService;
    }

    //all ledgers
    @GetMapping
    public String findAllLed(Model model) {
        model.addAttribute("title", "All Items In Stock");
        model.addAttribute("ledgers", ledgerService.findAll());
        return "ledger/ledger";
    }

    //reorder point < item count
    @GetMapping( "/reorderPoint" )
    public String reorderPoint(Model model) {
        model.addAttribute("title", "Reorder Point Limit Exceeded");
        model.addAttribute("ledgers", ledgerService.findAll()
                .stream()
                .filter(x -> Integer.parseInt(x.getQuantity()) < Integer.parseInt(x.getItem().getRop()))
                .collect(Collectors.toList()));
        return "ledger/ledger";
    }
    //near expired date
    @PostMapping("/expiredDate")
    public String expiredDate(@RequestAttribute( "startDate" ) LocalDate startDate,
                              @RequestAttribute( "endDate" ) LocalDate endDate, Model model){
        model.addAttribute("title", "All items on given date range start at "+startDate+" end at "+endDate);
        model.addAttribute("ledgers", ledgerService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate), dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate)));

        return "ledger/ledger";
    }

}
