package j_n_super_pvt_ltd.asset.branch.dao;

import j_n_super_pvt_ltd.asset.branch.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchDao extends JpaRepository<Branch, Integer> {
}
