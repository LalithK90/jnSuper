package J_N_Super_Pvt_Ltd.asset.invoice.service;

import J_N_Super_Pvt_Ltd.asset.invoice.dao.InvoiceDao;
import J_N_Super_Pvt_Ltd.asset.invoice.entity.Invoice;
import J_N_Super_Pvt_Ltd.asset.item.entity.Item;
import J_N_Super_Pvt_Ltd.util.interfaces.AbstractService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService implements AbstractService<Invoice, Integer> {
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
        return invoiceDao.save(invoice);
    }

    public boolean delete(Integer id) {
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
}
