package J_N_Super_Pvt_Ltd.asset.invoice.dao;


import J_N_Super_Pvt_Ltd.asset.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvoiceDao extends JpaRepository< Invoice, Integer > {
    List< Invoice > findByCreatedAtIsBetween(LocalDateTime form, LocalDateTime to);
}
