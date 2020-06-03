package lk.J_N_Super_Pvt_Ltd.asset.customer.dao;


import lk.J_N_Super_Pvt_Ltd.asset.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    Customer findFirstByOrderByIdDesc();
}
