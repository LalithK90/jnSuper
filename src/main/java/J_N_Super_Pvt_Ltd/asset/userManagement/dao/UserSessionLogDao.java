package lk.J_N_Super_Pvt_Ltd.asset.userManagement.dao;


import lk.J_N_Super_Pvt_Ltd.asset.userManagement.entity.Enum.UserSessionLogStatus;
import lk.J_N_Super_Pvt_Ltd.asset.userManagement.entity.User;
import lk.J_N_Super_Pvt_Ltd.asset.userManagement.entity.UserSessionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionLogDao extends JpaRepository<UserSessionLog, Integer > {
    UserSessionLog findByUserAndUserSessionLogStatus(User user, UserSessionLogStatus userSessionLogStatus);
}
