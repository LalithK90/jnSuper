package lk.j_n_super_pvt_ltd.asset.production_management.service;


import lk.j_n_super_pvt_ltd.asset.production_management.dao.ProductionDao;
import lk.j_n_super_pvt_ltd.asset.production_management.entity.Production;
import lk.j_n_super_pvt_ltd.util.interfaces.AbstractService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductionService implements AbstractService< Production, Integer > {
  private final ProductionDao productionDao;

  public ProductionService(ProductionDao productionDao) {
    this.productionDao = productionDao;
  }

  public List< Production > findAll() {
    return productionDao.findAll();
  }

  public Production findById(Integer id) {
    return productionDao.getOne(id);
  }

  public Production persist(Production Production) {

    return productionDao.save(Production);
  }

  public boolean delete(Integer id) {
    Production Production = productionDao.getOne(id);
    productionDao.save(Production);
    return false;
  }

  public List< Production > search(Production Production) {
    ExampleMatcher matcher = ExampleMatcher
        .matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example< Production > paymentExample = Example.of(Production, matcher);
    return productionDao.findAll(paymentExample);
  }

  public Production lastPayment() {
    return productionDao.findFirstByOrderByIdDesc();
  }

  public List< Production > findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to) {
    return productionDao.findByCreatedAtIsBetween(from, to);

  }

  public List< Production > findByCreatedAtIsBetweenAndCreatedBy(LocalDateTime from, LocalDateTime to, String userName) {
    return productionDao.findByCreatedAtIsBetweenAndCreatedBy(from, to, userName);
  }
}
