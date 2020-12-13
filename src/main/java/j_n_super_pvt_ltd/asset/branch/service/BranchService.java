package j_n_super_pvt_ltd.asset.branch.service;

import j_n_super_pvt_ltd.asset.branch.dao.BranchDao;
import j_n_super_pvt_ltd.asset.branch.entity.Branch;
import j_n_super_pvt_ltd.asset.common_asset.model.enums.LiveOrDead;
import j_n_super_pvt_ltd.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig( cacheNames = "branch" )
public class BranchService implements AbstractService<Branch, Integer> {
    private final BranchDao branchDao;

    @Autowired
    public BranchService(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public List<Branch> findAll() {
        return branchDao.findAll();
    }

    public Branch findById(Integer id) {
        return branchDao.getOne(id);
    }

    public Branch persist(Branch branch) {
        if(branch.getId()==null){
            branch.setLiveOrDead(LiveOrDead.ACTIVE);}
        return branchDao.save(branch);
    }

    public boolean delete(Integer id) {
       Branch branch =  branchDao.getOne(id);
        branch.setLiveOrDead(LiveOrDead.STOP);
        branchDao.save(branch);
        return false;
    }

    public List<Branch> search(Branch branch) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Branch> branchExample = Example.of(branch, matcher);
        return branchDao.findAll(branchExample);
    }
}
