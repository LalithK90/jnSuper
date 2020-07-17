package J_N_Super_Pvt_Ltd.asset.goodReceivedNote.service;


import J_N_Super_Pvt_Ltd.asset.goodReceivedNote.dao.GoodReceivedNoteDao;
import J_N_Super_Pvt_Ltd.asset.goodReceivedNote.entity.GoodReceivedNote;
import J_N_Super_Pvt_Ltd.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@CacheConfig(cacheNames = "goodReceivedNote")
public class GoodReceivedNoteService implements AbstractService<GoodReceivedNote, Integer> {
    private final GoodReceivedNoteDao goodReceivedNoteDao;

    @Autowired
    public GoodReceivedNoteService(GoodReceivedNoteDao goodReceivedNoteDao) {
        this.goodReceivedNoteDao = goodReceivedNoteDao;
    }

    @Override
    public Object findAll() {
        return goodReceivedNoteDao.findAll();
    }

    @Override
    public GoodReceivedNote findById(Integer id) {
        return goodReceivedNoteDao.getOne(id);
    }

    @Override
    public GoodReceivedNote persist(GoodReceivedNote goodReceivedNote) {
        return goodReceivedNoteDao.save(goodReceivedNote);
    }

    @Override
    public boolean delete(Integer id) {
        goodReceivedNoteDao.deleteById(id);
        return true;
    }

    @Override
    public List<GoodReceivedNote> search(GoodReceivedNote goodReceivedNote) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<GoodReceivedNote> goodReceivedNoteExample = Example.of(goodReceivedNote, matcher);
        return goodReceivedNoteDao.findAll(goodReceivedNoteExample);
    }
}