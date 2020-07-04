package J_N_Super_Pvt_Ltd.asset.PurchaseOrder.controller;



import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.PurchaseOrder;
import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.service.PurchaseOrderItemService;
import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.service.PurchaseOrderService;
import J_N_Super_Pvt_Ltd.asset.commonAsset.service.CommonService;
import J_N_Super_Pvt_Ltd.asset.item.service.ItemService;
import J_N_Super_Pvt_Ltd.asset.supplier.entity.Supplier;
import J_N_Super_Pvt_Ltd.asset.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/purchaseOrderItem")
public class PurchaseOrderItemController {
    private final ItemService itemService;
    private final PurchaseOrderService purchaseOrderService;
    private final CommonService commonService;
    private final PurchaseOrderItemService purchaseOrderItemService;


    @Autowired
    public PurchaseOrderItemController(ItemService itemService,PurchaseOrderService purchaseOrderService, CommonService commonService, PurchaseOrderItemService purchaseOrderItemService) {
        this.itemService = itemService;
        this.purchaseOrderService = purchaseOrderService;

        this.commonService = commonService;
        this.purchaseOrderItemService = purchaseOrderItemService;
    }




    //suppliers find form
    //suppler 1 or suppliers -> need to select 1
    // suppler with items form
    //save supplierItem

    @GetMapping
    public String addForm(Model model) {
        model.addAttribute("purchaseOrder", new PurchaseOrder());
        model.addAttribute("searchAreaShow", true);
        return "purchaseOrder/addpurchaseOrderItem";
    }

    @PostMapping("/find")
    public String search(@Valid @ModelAttribute Supplier supplier, Model model) {
        return commonService.supplierItemAndPurchaseOrderSearch(supplier, model, "purchaseOrder/addpurchaseOrderItem");
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        commonService.supplierItemAndPurchaseOrderView(model, id);
        model.addAttribute("items", itemService.findAll());
        return "purchaseOrder/addpurchaseOrderItem";
    }

  /*  @PostMapping
    public String purchaseOrderItemPersist(@Valid @ModelAttribute PurchaseOrder purchaseOrder, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        System.out.println(" i herer ");
        if (bindingResult.hasErrors()) {
            return "redirect:/purchaseOrderItem/" + purchaseOrder.getId();

        }
        for (PurchaseOrderItem purchaseOrderItem : purchaseOrder.getPurchaseOrderItems()) {
            purchaseOrderItem.setPurchaseOrder(purchaseOrder);
            //db purchaseOrder
            PurchaseOrderItem DBPurchaseOrderItem = PurchaseOrderItemService.findBypPurchaseOrderAndItem(purchaseOrder, purchaseOrderItem.getItem());
            if (DBPurchaseOrderItem != null) {
                DBPurchaseOrderItem.setPrice(PurchaseOrderItem.getPrice());
            }
            purchaseOrderItemService.persist(purchaseOrderItem);
        }
        return "redirect:/purchaseOrder";
    }*/

    @GetMapping("/purchaseOrder/{id}")
    public String addPriceToPurchaseOrderItem(@PathVariable int id, Model model) {
        PurchaseOrder purchaseOrder = purchaseOrderService.findById(id);
        purchaseOrder.setPurchaseOrderItems(purchaseOrderItemService.findByPurchaseOrder(purchaseOrder));
        model.addAttribute("purchaseOrderDetail", purchaseOrder);
        model.addAttribute("purchaseOrderrDetailShow", false);
        model.addAttribute("items", itemService.findAll());
        return "purchaseOrder/addpurchaseOrderItem";
    }

}
