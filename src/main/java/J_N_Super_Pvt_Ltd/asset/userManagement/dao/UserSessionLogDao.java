package J_N_Super_Pvt_Ltd.asset.userManagement.dao;


import J_N_Super_Pvt_Ltd.asset.userManagement.entity.Enum.UserSessionLogStatus;
import J_N_Super_Pvt_Ltd.asset.userManagement.entity.User;
import J_N_Super_Pvt_Ltd.asset.userManagement.entity.UserSessionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionLogDao extends JpaRepository<UserSessionLog, Integer > {
    UserSessionLog findByUserAndUserSessionLogStatus(User user, UserSessionLogStatus userSessionLogStatus);
}