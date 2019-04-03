package ua.com.sliusar.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.sliusar.domain.Product;
import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.persistent.Store;
import ua.com.sliusar.services.ProductService;
import ua.com.sliusar.validators.ValidationService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class ProductServiceImpl
 *
 * @author create by ivanslusar
 * 2/15/19
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private Store<Product> store;
    @Autowired
    private ValidationService validationService;

    public ProductServiceImpl() {

    }

    @Override
    public void create(Product product) {
        try {
            validationService.validateBigDecimal(product.getPrice().toString());
            store.add(product);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        store.update(product);
    }

    @Override
    public void delete(String id) {
        if (store.delete(Long.valueOf(id))) {
            System.out.println("Product was successes deleted");
        } else {
            System.out.println("Product wasn`t deleted");
        }
    }

    @Override
    public Product findById(String id) {
        return store.findById(Long.valueOf(id));
    }

    @Override
    public List<Product> findAll() {
        return store.findAll();
    }

}
