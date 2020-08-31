package j_n_super_pvt_ltd.asset.userManagement.dao;


import j_n_super_pvt_ltd.asset.userManagement.entity.Enum.UserSessionLogStatus;
import j_n_super_pvt_ltd.asset.userManagement.entity.User;
import j_n_super_pvt_ltd.asset.userManagement.entity.UserSessionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionLogDao extends JpaRepository<UserSessionLog, Integer > {
    UserSessionLog findByUserAndUserSessionLogStatus(User user, UserSessionLogStatus userSessionLogStatus);
}
