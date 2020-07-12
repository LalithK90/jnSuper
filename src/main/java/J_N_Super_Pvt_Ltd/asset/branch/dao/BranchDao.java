package J_N_Super_Pvt_Ltd.asset.branch.dao;


import J_N_Super_Pvt_Ltd.asset.branch.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchDao extends JpaRepository<Branch, Integer> {
}