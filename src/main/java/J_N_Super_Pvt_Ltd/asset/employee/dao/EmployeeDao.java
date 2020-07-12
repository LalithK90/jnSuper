package J_N_Super_Pvt_Ltd.asset.employee.dao;


import J_N_Super_Pvt_Ltd.asset.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    Employee findFirstByOrderByIdDesc();

    Employee findByNic(String nic);
}

