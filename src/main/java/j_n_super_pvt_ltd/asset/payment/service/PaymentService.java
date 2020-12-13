package j_n_super_pvt_ltd.asset.payment.service;


import j_n_super_pvt_ltd.asset.common_asset.model.enums.LiveOrDead;
import j_n_super_pvt_ltd.asset.payment.dao.PaymentDao;
import j_n_super_pvt_ltd.asset.purchase_order.entity.PurchaseOrder;
import j_n_super_pvt_ltd.util.interfaces.AbstractService;
import j_n_super_pvt_ltd.asset.payment.entity.Payment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements AbstractService< Payment, Integer > {
    private final PaymentDao paymentDao;

    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public List< Payment > findAll() {
        return paymentDao.findAll();
    }

    public Payment findById(Integer id) {
        return paymentDao.getOne(id);
    }

    public Payment persist(Payment payment) {
        if(payment.getId()==null){
            payment.setLiveOrDead(LiveOrDead.ACTIVE);}
        return paymentDao.save(payment);
    }

    public boolean delete(Integer id) {
        Payment payment =  paymentDao.getOne(id);
        payment.setLiveOrDead(LiveOrDead.STOP);
        paymentDao.save(payment);
        return false;
    }
    public List< Payment > search(Payment payment) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example< Payment > paymentExample = Example.of(payment, matcher);
        return paymentDao.findAll(paymentExample);
    }

    public List< Payment > findByPurchaseOrder(PurchaseOrder purchaseOrder) {
        return paymentDao.findByPurchaseOrder(purchaseOrder);
    }

    public Payment lastPayment() {
        return paymentDao.findFirstByOrderByIdDesc();
    }
}
