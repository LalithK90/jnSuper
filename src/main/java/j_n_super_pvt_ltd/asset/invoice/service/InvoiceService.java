package j_n_super_pvt_ltd.asset.invoice.service;

import j_n_super_pvt_ltd.asset.common_asset.model.enums.LiveOrDead;
import j_n_super_pvt_ltd.util.interfaces.AbstractService;
import j_n_super_pvt_ltd.asset.invoice.dao.InvoiceDao;
import j_n_super_pvt_ltd.asset.invoice.entity.Invoice;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService implements AbstractService< Invoice, Integer > {
    private final InvoiceDao invoiceDao;

    public InvoiceService(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }


    public List< Invoice > findAll() {
        return invoiceDao.findAll();
    }

    public Invoice findById(Integer id) {
        return invoiceDao.getOne(id);
    }

    public Invoice persist(Invoice invoice) {
        if(invoice.getId()==null){
            invoice.setLiveOrDead(LiveOrDead.ACTIVE);}
        return invoiceDao.save(invoice);
    }

    public boolean delete(Integer id) {
        Invoice invoice =  invoiceDao.getOne(id);
        invoice.setLiveOrDead(LiveOrDead.STOP);
        invoiceDao.save(invoice);
        return false;
    }
    public List< Invoice > search(Invoice invoice) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example< Invoice > invoiceExample = Example.of(invoice, matcher);
        return invoiceDao.findAll(invoiceExample);

    }

    public List< Invoice > findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to) {
        return invoiceDao.findByCreatedAtIsBetween(from, to);
    }

    public Invoice findByLastInvoice() {
        return invoiceDao.findFirstByOrderByIdDesc();
    }
}
