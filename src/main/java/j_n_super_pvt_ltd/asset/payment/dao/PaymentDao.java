package j_n_super_pvt_ltd.asset.payment.dao;


import j_n_super_pvt_ltd.asset.purchaseOrder.entity.PurchaseOrder;
import j_n_super_pvt_ltd.asset.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDao extends JpaRepository<Payment,Integer> {
    List< Payment> findByPurchaseOrder(PurchaseOrder purchaseOrder);

    Payment findFirstByOrderByIdDesc();
}
