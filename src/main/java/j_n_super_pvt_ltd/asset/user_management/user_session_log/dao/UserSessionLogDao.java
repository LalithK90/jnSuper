package j_n_super_pvt_ltd.asset.user_management.user_session_log.dao;

import lk.j_n_super_pvt_ltd.asset.user_management.user_session_log.entity.enums.UserSessionLogStatus;
import lk.j_n_super_pvt_ltd.asset.user_management.user.entity.User;
import lk.j_n_super_pvt_ltd.asset.user_management.user_session_log.entity.UserSessionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionLogDao extends JpaRepository<UserSessionLog, Integer > {
    UserSessionLog findByUserAndUserSessionLogStatus(User user, UserSessionLogStatus userSessionLogStatus);
}
