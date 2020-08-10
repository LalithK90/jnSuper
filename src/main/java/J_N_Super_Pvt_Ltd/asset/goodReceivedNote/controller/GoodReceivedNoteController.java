package J_N_Super_Pvt_Ltd.asset.goodReceivedNote.controller;


import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.Enum.PurchaseOrderStatus;
import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.service.PurchaseOrderService;
import J_N_Super_Pvt_Ltd.asset.goodReceivedNote.entity.GoodReceivedNote;
import J_N_Super_Pvt_Ltd.asset.goodReceivedNote.service.GoodReceivedNoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("grn", new GoodReceivedNote());
        return "goodReceivedNote/addGoodReceivedNote";
    }


}
