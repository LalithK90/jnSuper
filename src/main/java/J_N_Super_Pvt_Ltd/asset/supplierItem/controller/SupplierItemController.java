package J_N_Super_Pvt_Ltd.asset.supplierItem.controller;


import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.commonModel.PurchaseOrderItemLedger;
import J_N_Super_Pvt_Ltd.asset.commonAsset.service.CommonService;
import J_N_Super_Pvt_Ltd.asset.item.entity.Item;
import J_N_Super_Pvt_Ltd.asset.item.service.ItemService;
import J_N_Super_Pvt_Ltd.asset.ledger.dao.LedgerDao;
import J_N_Super_Pvt_Ltd.asset.supplier.entity.Supplier;
import J_N_Super_Pvt_Ltd.asset.supplier.service.SupplierService;
import J_N_Super_Pvt_Ltd.asset.supplierItem.entity.Enum.ItemSupplierStatus;
import J_N_Super_Pvt_Ltd.asset.supplierItem.entity.SupplierItem;
import J_N_Super_Pvt_Ltd.asset.supplierItem.service.SupplierItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/supplierItem")
public class SupplierItemController {
    private final ItemService itemService;
    private final SupplierService supplierService;
    private final CommonService commonService;
    private final SupplierItemService supplierItemService;
    private final LedgerDao ledgerDao;


    @Autowired
    public SupplierItemController(ItemService itemService, SupplierService supplierService, CommonService commonService, SupplierItemService supplierItemService, LedgerDao ledgerDao) {
        this.itemService = itemService;
        this.supplierService = supplierService;
        this.commonService = commonService;
        this.supplierItemService = supplierItemService;
        this.ledgerDao = ledgerDao;
    }

    @GetMapping
    public String addForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("searchAreaShow", true);
        List<Object> objects = new ArrayList<>();
        model.addAttribute("currentlyBuyingItems", objects);
        return "supplier/addSupplierItem";
    }

    @PostMapping("/find")
    public String search(@Valid @ModelAttribute Supplier supplier, Model model) {
        return commonService.supplierItem(supplier, model, "purchaseOrder/addPurchaseOrder");

    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        return commonService.supplierItem(model, id);
    }

    @PostMapping
    public String supplierItemPersist(@Valid @ModelAttribute("supplier") Supplier supplier, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            var value = false;
            for (SupplierItem supplierItem : supplier.getSupplierItems()) {
                if (supplierItem.getId() != null) {
                    value = true;
                    break;
                }
            }
            if (value) {
                return "redirect:/supplierItem/edit/" + supplier.getId();
            } else {
                return "redirect:/supplierItem/" + supplier.getId();
            }
        }
        //items from front item relevant to supplier
        List<SupplierItem> supplierItems = supplier.getSupplierItems();
        for (SupplierItem supplierItem : supplierItems) {
            if (supplierItem.getId() == null) {
                supplierItem.setSupplier(supplier);
            }
            supplierItemService.persist(supplierItem);
        }
        return "redirect:/supplier";
    }

    @GetMapping("/supplier/{id}")
    public String addPriceToSupplierItem(@PathVariable int id, Model model) {
        Supplier supplier = supplierService.findById(id);
        List<SupplierItem> supplierItems = supplierItemService.findBySupplier(supplier);
        model.addAttribute("itemSupplierStatus", ItemSupplierStatus.values());
        model.addAttribute("supplierDetail", supplier);
        model.addAttribute("supplierDetailShow", false);
        model.addAttribute("supplierItemEdit", false);
        model.addAttribute("currentlyBuyingItems", supplierItems);

        List<Item> items = itemService.findAll();

        if (!supplierItems.isEmpty()) {
            for (Item item : itemService.findAll()) {
                for (SupplierItem supplierItem : supplierItems) {
                    if (item.equals(supplierItem.getItem())) {
                        items.remove(item);
                    }
                }
            }
        }

        model.addAttribute("items", items);
        return "supplier/addSupplierItem";
    }

    @GetMapping("/edit/{id}")
    public String addEditToSupplierItem(@PathVariable int id, Model model) {
        Supplier supplier = supplierService.findById(id);
        List<SupplierItem> supplierItems = supplierItemService.findBySupplier(supplier);
        model.addAttribute("itemSupplierStatus", ItemSupplierStatus.values());
        model.addAttribute("supplierDetail", supplier);
        model.addAttribute("supplierDetailShow", false);
        model.addAttribute("currentlyBuyingItems", supplierItems);
        model.addAttribute("supplierItemEdit", true);
        return "supplier/addSupplierItem";
    }

    @GetMapping(value = "/supplierItem", params = {"supplierId", "itemId"})
    @ResponseBody
    public PurchaseOrderItemLedger purchaseOrderSupplierItem(@RequestParam("supplierId") Integer supplierId, @RequestParam("itemId") Integer itemId) {
        //supplier id
        Supplier supplier = new Supplier();
        supplier.setId(supplierId);
        // item id
        Item item = new Item();
        item.setId(itemId);

        SupplierItem supplierItem = supplierItemService.findBySupplierAndItem(supplier, item);
        PurchaseOrderItemLedger purchaseOrderItemLedger = new PurchaseOrderItemLedger();
        /* 1. item ID   2. Item name 3. Rop 4. Price 5. Available Quantity. */
/*        purchaseOrderItemLedger.setItemId(supplierItem.getItem().getId());
        purchaseOrderItemLedger.setItemName(supplierItem.getItem().getName());
        purchaseOrderItemLedger.setRop(supplierItem.getItem().getRop());
        purchaseOrderItemLedger.setPrice(supplierItem.getPrice());
        ItemBatch itemBatch = itemBatchService.findByItemAndSupplier(item,supplier);
        purchaseOrderItemLedger.setAvailableQuantity(ledgerService.findByItemBatch(itemBatch).getQuantity());*/

        /*Dumy data to frontend*/
        purchaseOrderItemLedger.setItemId(1);
        purchaseOrderItemLedger.setItemName("Name");
        purchaseOrderItemLedger.setRop("190");
        purchaseOrderItemLedger.setPrice(BigDecimal.valueOf(123.50));
        purchaseOrderItemLedger.setAvailableQuantity("100");

        return purchaseOrderItemLedger;
    }


}
