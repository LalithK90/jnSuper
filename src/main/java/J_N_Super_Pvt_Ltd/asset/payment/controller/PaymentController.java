package J_N_Super_Pvt_Ltd.asset.payment.controller;

import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.Enum.PurchaseOrderStatus;
import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.PurchaseOrder;
import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.service.PurchaseOrderService;
import J_N_Super_Pvt_Ltd.asset.goodReceivedNote.entity.Enum.GoodReceivedNoteState;
import J_N_Super_Pvt_Ltd.asset.goodReceivedNote.entity.GoodReceivedNote;
import J_N_Super_Pvt_Ltd.asset.goodReceivedNote.service.GoodReceivedNoteService;
import J_N_Super_Pvt_Ltd.asset.invoice.entity.Enum.PaymentMethod;
import J_N_Super_Pvt_Ltd.asset.payment.entity.Payment;
import J_N_Super_Pvt_Ltd.asset.payment.service.PaymentService;
import J_N_Super_Pvt_Ltd.util.service.OperatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping( "/payment" )
public class PaymentController {

    private final PaymentService paymentService;
    private final PurchaseOrderService purchaseOrderService;
    private final OperatorService operatorService;
    private final GoodReceivedNoteService goodReceivedNoteService;

    public PaymentController(PaymentService paymentService, PurchaseOrderService purchaseOrderService,
                             OperatorService operatorService, GoodReceivedNoteService goodReceivedNoteService) {
        this.paymentService = paymentService;
        this.purchaseOrderService = purchaseOrderService;
        this.operatorService = operatorService;
        this.goodReceivedNoteService = goodReceivedNoteService;
    }


    @GetMapping
    public String getAllPurchaseOrderToPay(Model model) {
        //find all purchase order to have to pay using purchase order status
        //1. still not processed po 2. partially paid po
        List< PurchaseOrder > purchaseOrdersDB =
                purchaseOrderService.findByPurchaseOrderStatus(PurchaseOrderStatus.NOT_PROCEED);
        //need to pay po
        List< PurchaseOrder > purchaseOrders = new ArrayList<>();

        if ( purchaseOrdersDB != null ) {
            for ( PurchaseOrder purchaseOrder : purchaseOrdersDB ) {
                List< Payment > payments = paymentService.findByPurchaseOrder(purchaseOrder);
                if ( payments != null ) {
                    BigDecimal paidAmount = BigDecimal.ZERO;
                    for ( Payment payment : payments ) {
                        paidAmount = operatorService.addition(paidAmount, payment.getAmount());
                    }
                    purchaseOrder.setPrice(operatorService.subtraction(purchaseOrder.getPrice(), paidAmount));
                }
                purchaseOrders.add(purchaseOrder);
            }
        }
        model.addAttribute("purchaseOrders", purchaseOrders);
        return "payment/payment";
    }

    @GetMapping( "/{id}" )
    public String addPaymentAmount(@PathVariable( "id" ) Integer id, Model model) {
        //payment need to make
        PurchaseOrder purchaseOrderNeedToPay = purchaseOrderService.findById(id);

        //1. still not processed po 2. partially paid po
        List< PurchaseOrder > purchaseOrdersDB =
                purchaseOrderService.findByPurchaseOrderStatusAndSupplier(PurchaseOrderStatus.NOT_PROCEED,
                                                                          purchaseOrderNeedToPay.getSupplier());
        List< PurchaseOrder > purchaseOrderNotPaid = new ArrayList<>();

        if ( purchaseOrdersDB != null ) {
            for ( PurchaseOrder purchaseOrder : purchaseOrdersDB ) {
                List< Payment > payments = paymentService.findByPurchaseOrder(purchaseOrder);
                if ( payments != null ) {
                    BigDecimal paidAmount = BigDecimal.ZERO;
                    for ( Payment payment : payments ) {
                        paidAmount = operatorService.addition(paidAmount, payment.getAmount());
                    }
                    if ( goodReceivedNoteService.findByPurchaseOrder(purchaseOrder) != null ) {
                        purchaseOrder.setGrnAt(goodReceivedNoteService.findByPurchaseOrder(purchaseOrder).getCreatedAt());
                    } else {
                        purchaseOrder.setGrnAt(LocalDateTime.now());
                    }
                    purchaseOrder.setPaidAmount(paidAmount);
                    purchaseOrder.setNeedToPaid(operatorService.subtraction(purchaseOrder.getPrice(), paidAmount));
                }
                purchaseOrderNotPaid.add(purchaseOrder);
            }
        }
        model.addAttribute("payment", new Payment());
        model.addAttribute("purchaseOrders", purchaseOrderNotPaid);
        model.addAttribute("purchaseOrderNeedToPay", purchaseOrderNeedToPay);
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "payment/makePayment";
    }

    @PostMapping
    public String savePayment(@Valid @ModelAttribute Payment payment, BindingResult bindingResult) {
        if ( bindingResult.hasErrors() ) {
            return "redirect:/payment/".concat(String.valueOf(payment.getPurchaseOrder().getId()));
        }
        //1. need to save payment
        Payment paymentDB = paymentService.persist(payment);
        PurchaseOrder purchaseOrder = paymentDB.getPurchaseOrder();
        //2. check po state -> need to finished all payment to change this
        //3. check grn state -> need to finished all payment to change this
        List< Payment > payments = paymentService.findByPurchaseOrder(purchaseOrder);
        if ( payments != null ) {
            BigDecimal paidAmount = BigDecimal.ZERO;
            for ( Payment paymentOne : payments ) {
                paidAmount = operatorService.addition(paidAmount, paymentOne.getAmount());
            }
            // if check all paid amount is equal or not purchase order amount
            if ( paidAmount.equals(purchaseOrder.getPrice()) ) {
                //change GRN sate
                GoodReceivedNote goodReceivedNote = goodReceivedNoteService.findByPurchaseOrder(purchaseOrder);
                goodReceivedNote.setGoodReceivedNoteState(GoodReceivedNoteState.PAID);
                goodReceivedNoteService.persist(goodReceivedNote);
                //change purchase order status
                purchaseOrder.setPurchaseOrderStatus(PurchaseOrderStatus.COMPLETED);
                purchaseOrderService.persist(purchaseOrder);
            }
        }
        return "redirect:/payment";
    }

}
