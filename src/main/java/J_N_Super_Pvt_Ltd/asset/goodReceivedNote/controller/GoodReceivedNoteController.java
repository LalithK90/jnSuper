package J_N_Super_Pvt_Ltd.asset.goodReceivedNote.controller;


import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.Enum.PurchaseOrderStatus;
import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.service.PurchaseOrderService;
import J_N_Super_Pvt_Ltd.asset.goodReceivedNote.entity.GoodReceivedNote;
import J_N_Super_Pvt_Ltd.asset.goodReceivedNote.service.GoodReceivedNoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping( "/goodReceivedNote" )
public class GoodReceivedNoteController {
    private final GoodReceivedNoteService goodReceivedNoteService;
    private final PurchaseOrderService purchaseOrderService;

    public GoodReceivedNoteController(GoodReceivedNoteService goodReceivedNoteService,
                                      PurchaseOrderService purchaseOrderService) {
        this.goodReceivedNoteService = goodReceivedNoteService;
        this.purchaseOrderService = purchaseOrderService;
    }


    @GetMapping
    public String notCompleteAll(Model model) {
        model.addAttribute("notCompleteAll",
                           purchaseOrderService.findByGoodReceivedNoteState(PurchaseOrderStatus.NOT_COMPLETED));
        return "goodReceivedNote/goodReceivedNote";
    }

    @GetMapping( "/{id}" )
    public String grnAdd(@PathVariable Integer id, Model model) {
        model.addAttribute("purchaseOrderDetail", purchaseOrderService.findById(id));
        model.addAttribute("goodReceivedNote", new GoodReceivedNote());
        return "goodReceivedNote/addGoodReceivedNote";
    }

    @PostMapping( "/received" )
    public String saveGRN(@Valid @ModelAttribute GoodReceivedNote goodReceivedNote, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {
        if ( bindingResult.hasErrors() ) {
            return "redirect:/goodReceivedNote/".concat(String.valueOf(goodReceivedNote.getPurchaseOrder().getId()));
        }

        /*
       received parameter -> ledgers
       1. item ->id
       2. quantity
       3. sell price
       4. mDate
       5. eDate
       goodReceivedNote
        1. remarks
        2. totalPrice
        3. purchaseOrder -> id

        * */


        return "redirect:/goodReceivedNote";
    }

}
