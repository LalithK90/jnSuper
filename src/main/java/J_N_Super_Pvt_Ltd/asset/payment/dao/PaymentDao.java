package J_N_Super_Pvt_Ltd.asset.payment.dao;


import J_N_Super_Pvt_Ltd.asset.PurchaseOrder.entity.PurchaseOrder;
import J_N_Super_Pvt_Ltd.asset.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDao extends JpaRepository<Payment,Integer> {
    List< Payment> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
