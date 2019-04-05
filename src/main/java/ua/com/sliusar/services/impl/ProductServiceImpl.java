package ua.com.sliusar.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.sliusar.domain.Product;
import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.persistent.ProductRepository;
import ua.com.sliusar.validators.ValidationService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ProductServiceImpl
 *
 * @author create by ivanslusar
 * 2/15/19
 */
@Service
@Transactional
public class ProductServiceImpl {
    @Autowired
    private ProductRepository store;
    @Autowired
    private ValidationService validationService;

    public ProductServiceImpl() {

    }

    public void create(Product product) {
        try {
            validationService.validateBigDecimal(product.getPrice().toString());
            store.save(product);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    public void update(Product product) {
        store.save(product);
    }

    public void delete(String id) {
        store.deleteById(Long.valueOf(id));
    }

    public Product findById(String id) {
        return store.findById(Long.valueOf(id)).get();
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        store.findAll().forEach(product -> products.add(product));
        return products;
    }

}
