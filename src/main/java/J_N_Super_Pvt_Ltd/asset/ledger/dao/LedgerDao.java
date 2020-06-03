package lk.J_N_Super_Pvt_Ltd.asset.ledger.dao;


import lk.J_N_Super_Pvt_Ltd.asset.ledger.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerDao extends JpaRepository<Ledger, Integer> {
}
