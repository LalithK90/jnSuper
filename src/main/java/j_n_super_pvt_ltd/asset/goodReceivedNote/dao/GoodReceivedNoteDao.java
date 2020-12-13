package j_n_super_pvt_ltd.asset.goodReceivedNote.dao;


import j_n_super_pvt_ltd.asset.purchaseOrder.entity.PurchaseOrder;
import j_n_super_pvt_ltd.asset.goodReceivedNote.entity.GoodReceivedNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodReceivedNoteDao extends JpaRepository<GoodReceivedNote, Integer> {
    GoodReceivedNote findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
