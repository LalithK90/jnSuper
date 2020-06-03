package lk.J_N_Super_Pvt_Ltd.asset.userManagement.dao;

import lk.J_N_Super_Pvt_Ltd.asset.userManagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer > {
    Role findByRoleName(String roleName);
}
