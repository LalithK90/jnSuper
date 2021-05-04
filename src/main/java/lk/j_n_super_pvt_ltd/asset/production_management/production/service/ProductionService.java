package lk.j_n_super_pvt_ltd.asset.production_management.production.service;

import lk.j_n_super_pvt_ltd.asset.invoice.entity.enums.InvoiceValidOrNot;
import lk.j_n_super_pvt_ltd.asset.production_management.production.dao.ProductionDao;
import lk.j_n_super_pvt_ltd.asset.production_management.production.entity.enums.ProductionStatus;
import lk.j_n_super_pvt_ltd.util.interfaces.AbstractService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import lk.j_n_super_pvt_ltd.asset.production_management.production.entity.Production;

import javax.management.loading.MLetContent;
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

  public Production persist(Production production) {
    if ( production.getId() == null ) {
      production.setInvoiceValidOrNot(InvoiceValidOrNot.VALID);
      production.setProductionStatus(ProductionStatus.INI);
    }
    return productionDao.save(production);
  }

  public boolean delete(Integer id) {
    Production production = productionDao.getOne(id);
    productionDao.save(production);
    return false;
  }

  public List< Production > search(Production production) {
    ExampleMatcher matcher = ExampleMatcher
        .matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example< Production > paymentExample = Example.of(production, matcher);
    return productionDao.findAll(paymentExample);
  }

  public Production lastPayment() {
    return productionDao.findFirstByOrderByIdDesc();
  }

  public List< Production > findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to) {
    return productionDao.findByCreatedAtIsBetween(from, to);

  }

  public List< Production > findByCreatedAtIsBetweenAndCreatedBy(LocalDateTime from, LocalDateTime to,
                                                                 String userName) {
    return productionDao.findByCreatedAtIsBetweenAndCreatedBy(from, to, userName);
  }

  public Production findByLastInvoice() {
    return productionDao.findFirstByOrderByIdDesc();
  }
}
