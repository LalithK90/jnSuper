package J_N_Super_Pvt_Ltd.asset.payment.controller;

import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.Enum.PurchaseOrderStatus;
import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.PurchaseOrder;
import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.service.PurchaseOrderService;
import J_N_Super_Pvt_Ltd.asset.payment.entity.Payment;
import J_N_Super_Pvt_Ltd.asset.payment.service.PaymentService;
import J_N_Super_Pvt_Ltd.util.service.OperatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping( "/payment" )
public class PaymentController {

    private final PaymentService paymentService;
    private final PurchaseOrderService purchaseOrderService;
    private final OperatorService operatorService;

    public PaymentController(PaymentService paymentService, PurchaseOrderService purchaseOrderService,
                             OperatorService operatorService) {
        this.paymentService = paymentService;
        this.purchaseOrderService = purchaseOrderService;
        this.operatorService = operatorService;
    }


    @GetMapping
    public String getAllPurchaseOrderToPay(Model model) {
        List< PurchaseOrder > purchaseOrders = new ArrayList<>();
        for ( PurchaseOrder purchaseOrder :
                purchaseOrderService.findByPurchaseOrderStatus(PurchaseOrderStatus.NOT_PROCEED) ) {
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
        model.addAttribute("payments", purchaseOrders);
        return "payment/payment";
    }
}
