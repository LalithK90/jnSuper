package lk.JNSuper.JN.asset.userManagement.dao;

import lk.JNSuper.JN.asset.userManagement.entity.Enum.UserSessionLogStatus;
import lk.JNSuper.JN.asset.userManagement.entity.User;
import lk.JNSuper.JN.asset.userManagement.entity.UserSessionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionLogDao extends JpaRepository<UserSessionLog, Integer > {
    UserSessionLog findByUserAndUserSessionLogStatus(User user, UserSessionLogStatus userSessionLogStatus);
}
