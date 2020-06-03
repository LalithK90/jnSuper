package lk.J_N_Super_Pvt_Ltd.asset.invoice.dao;

import lk.J_N_Super_Pvt_Ltd.asset.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDao extends JpaRepository<Invoice,Integer> {
}
