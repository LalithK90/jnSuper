package j_n_super_pvt_ltd.asset.good_received_note.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import j_n_super_pvt_ltd.asset.good_received_note.entity.enums.GoodReceivedNoteState;
import j_n_super_pvt_ltd.asset.ledger.entity.Ledger;
import j_n_super_pvt_ltd.asset.common_asset.model.enums.LiveOrDead;
import j_n_super_pvt_ltd.asset.purchase_order.entity.PurchaseOrder;
import j_n_super_pvt_ltd.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("GoodReceivedNote")
public class GoodReceivedNote extends AuditEntity {
    private String remarks;

    @Column( precision = 10, scale = 2 )
    private BigDecimal totalAmount;

    @Enumerated( EnumType.STRING )
    private GoodReceivedNoteState goodReceivedNoteState;

    @Enumerated(EnumType.STRING)
    private LiveOrDead liveOrDead;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @OneToMany( mappedBy = "goodReceivedNote", cascade = CascadeType.PERSIST)
    private List<Ledger> ledgers;


}
