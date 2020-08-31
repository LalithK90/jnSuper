package j_n_super_pvt_ltd.asset.employee.dao;


import j_n_super_pvt_ltd.asset.employee.entity.Employee;
import j_n_super_pvt_ltd.asset.employee.entity.EmployeeFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public interface EmployeeFilesDao extends JpaRepository<EmployeeFiles, Integer > {
    List< EmployeeFiles > findByEmployeeOrderByIdDesc(Employee employee);

    EmployeeFiles findByName(String filename);

    EmployeeFiles findByNewName(String filename);

    EmployeeFiles findByNewId(String filename);
}