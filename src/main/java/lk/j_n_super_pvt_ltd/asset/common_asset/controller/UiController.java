package lk.j_n_super_pvt_ltd.asset.common_asset.controller;


import lk.j_n_super_pvt_ltd.asset.ledger.service.LedgerService;
import lk.j_n_super_pvt_ltd.asset.user_management.user.service.UserService;
import lk.j_n_super_pvt_ltd.util.service.DateTimeAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Controller
public class UiController {

    private final UserService userService;
    private final DateTimeAgeService dateTimeAgeService;
    private final LedgerService ledgerService;

    @Autowired
    public UiController(UserService userService, DateTimeAgeService dateTimeAgeService, LedgerService ledgerService) {
        this.userService = userService;
        this.dateTimeAgeService = dateTimeAgeService;
        this.ledgerService = ledgerService;
    }

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = {"/home", "/mainWindow"})
    public String getHome(Model model) {

        model.addAttribute("ropList", ledgerService.findAll()
            .stream()
            .filter(x -> Integer.parseInt(x.getQuantity()) < Integer.parseInt(x.getItem().getRop()))
            .collect(Collectors.toList()));
        LocalDate today = LocalDate.now();

        model.addAttribute("exList",
                           ledgerService.findByExpiredDateBetween(today.minusDays(10), today));

        return "mainWindow";
    }

    @GetMapping(value = {"/login"})
    public String getLogin() {
        return "login/login";
    }

    @GetMapping(value = {"/login/error10"})
    public String getLogin10(Model model) {
        model.addAttribute("err", "You already entered wrong credential more than 10 times. \n Please meet the system" +
                " admin");
        return "login/login";
    }

    @GetMapping(value = {"/login/noUser"})
    public String getLoginNoUser(Model model) {
        model.addAttribute("err", "There is no user according to the user name. \n Please try again !!");
        return "login/login";
    }




}
