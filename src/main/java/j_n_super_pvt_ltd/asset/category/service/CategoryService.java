package j_n_super_pvt_ltd.asset.category.service;


import j_n_super_pvt_ltd.asset.category.dao.CategoryDao;
import j_n_super_pvt_ltd.asset.common_asset.model.enums.LiveOrDead;
import j_n_super_pvt_ltd.util.interfaces.AbstractService;
import j_n_super_pvt_ltd.asset.category.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig( cacheNames = "category" )
public class CategoryService implements AbstractService< Category, Integer > {
  private final CategoryDao categoryDao;

  @Autowired
  public CategoryService(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  public List< Category > findAll() {
    return categoryDao.findAll();
  }

  public Category findById(Integer id) {
    return categoryDao.getOne(id);
  }

  public Category persist(Category category) {
    if ( category.getId() == null ) {
      category.setLiveOrDead(LiveOrDead.ACTIVE);
    }
    return categoryDao.save(category);
  }

  public boolean delete(Integer id) {
    Category category = categoryDao.getOne(id);
    category.setLiveOrDead(LiveOrDead.STOP);
    categoryDao.save(category);
    return false;
  }

  public List< Category > search(Category category) {
    ExampleMatcher matcher = ExampleMatcher
        .matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example< Category > categoryExample = Example.of(category, matcher);
    return categoryDao.findAll(categoryExample);
  }
}
