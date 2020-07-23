package J_N_Super_Pvt_Ltd.asset.itemBatch.controller;


import J_N_Super_Pvt_Ltd.asset.item.entity.Item;
import J_N_Super_Pvt_Ltd.asset.item.service.ItemService;
import J_N_Super_Pvt_Ltd.asset.itemBatch.entity.ItemBatch;
import J_N_Super_Pvt_Ltd.asset.itemBatch.service.ItemBatchService;
import J_N_Super_Pvt_Ltd.asset.supplierItem.service.SupplierItemService;
import J_N_Super_Pvt_Ltd.util.service.MakeAutoGenerateNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/itemBatch")
public class ItemBatchController {
    private final ItemBatchService itemBatchService;
    private final SupplierItemService supplierItemService;
    private final ItemService itemService;
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

    @Autowired
    public ItemBatchController(ItemBatchService itemBatchService, SupplierItemService supplierItemService, ItemService itemService, MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
        this.itemBatchService = itemBatchService;
        this.supplierItemService = supplierItemService;
        this.itemService = itemService;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    }



    @GetMapping("/itemBatch/{id}")
    public String itemBatch(@PathVariable Integer id, Model model) {
        Item item = itemService.findById(id);
        model.addAttribute("itemDetail", item);
        model.addAttribute("suppliers",supplierItemService.findByItem(item));
       model.addAttribute("itemBatch", new ItemBatch());
        return "itemBatch/addItemBatch";
    }

}