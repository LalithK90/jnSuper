package J_N_Super_Pvt_Ltd.asset.userManagement.service;


import J_N_Super_Pvt_Ltd.asset.userManagement.dao.UserSessionLogDao;
import J_N_Super_Pvt_Ltd.asset.userManagement.entity.Enum.UserSessionLogStatus;
import J_N_Super_Pvt_Ltd.asset.userManagement.entity.User;
import J_N_Super_Pvt_Ltd.asset.userManagement.entity.UserSessionLog;
import J_N_Super_Pvt_Ltd.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@CacheConfig( cacheNames = {"userSessionLog"} )
public class UserSessionLogService implements AbstractService<UserSessionLog, Integer > {
    private final UserSessionLogDao userSessionLogDao;

    @Autowired
    public UserSessionLogService(UserSessionLogDao userSessionLogDao) {
        this.userSessionLogDao = userSessionLogDao;
    }

    @Override
    @Cacheable
    public List<UserSessionLog> findAll() {
        return userSessionLogDao.findAll();
    }

    @Override
    @Cacheable
    public UserSessionLog findById(Integer id) {
        return userSessionLogDao.getOne(id);
    }

    @Override
    @Caching( evict = {@CacheEvict( value = "userSessionLog", allEntries = true )},
            put = {@CachePut( value = "userSessionLog", key = "#userSessionLog.id" )} )
    public UserSessionLog persist(UserSessionLog userSessionLog) {
        return userSessionLogDao.save(userSessionLog);
    }

    @Override
    public boolean delete(Integer id) {
        // can not be implement

        return true;
    }

    public void delete(UserSessionLog userSessionLog){
        userSessionLogDao.delete(userSessionLog);
    }

    @Override
    public List<UserSessionLog> search(UserSessionLog userSessionLog) {
        return null;
    }

    @Cacheable
    public UserSessionLog findByUserAndUserSessionLogStatus(User user, UserSessionLogStatus userSessionLogStatus) {
        return userSessionLogDao.findByUserAndUserSessionLogStatus(user, userSessionLogStatus);
    }
}

