package j_n_super_pvt_ltd.asset.ledger.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import j_n_super_pvt_ltd.asset.goodReceivedNote.entity.GoodReceivedNote;
import j_n_super_pvt_ltd.asset.item.entity.Item;
import j_n_super_pvt_ltd.util.audit.AuditEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter( "Ledger" )
public class Ledger extends AuditEntity {

    @NotEmpty
    private String quantity;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal sellPrice;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate manufactureDate;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate expiredDate;

    @ManyToOne
    @JsonManagedReference
    private Item item;

    @ManyToOne
    @JsonIgnore
    private GoodReceivedNote goodReceivedNote;


}
