package J_N_Super_Pvt_Ltd.asset.item.entity;


import J_N_Super_Pvt_Ltd.asset.invoice.entity.InvoiceItemQuantity;
import J_N_Super_Pvt_Ltd.asset.item.entity.Enum.ItemStatus;
import J_N_Super_Pvt_Ltd.asset.ledger.entity.Ledger;
import J_N_Super_Pvt_Ltd.util.audit.AuditEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("ItemBatch")
public class ItemBatch extends AuditEntity {

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Column(unique = true)
    private String batch;

    @Column(unique = true)
    private String code;

    private BigDecimal price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate mDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;
    @OneToMany(mappedBy = "itemBatch")
    private List<Ledger> ledgers;

    @OneToMany(mappedBy = "itemBatch")
    private List<InvoiceItemQuantity> invoiceItemQuantities;
}
