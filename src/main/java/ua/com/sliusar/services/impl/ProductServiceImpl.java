package ua.com.sliusar.services.impl;

import org.springframework.stereotype.Service;
import ua.com.sliusar.domain.Product;
import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.persistent.ProductStore;
import ua.com.sliusar.persistent.Store;
import ua.com.sliusar.services.ProductService;
import ua.com.sliusar.validators.ValidationService;
import ua.com.sliusar.validators.impl.ValidationServiceImp;

import java.util.List;

/**
 * Class ProductServiceImpl
 *
 * @author create by ivanslusar
 * 2/15/19
 */
@Service
public class ProductServiceImpl implements ProductService {
    private static final Store<Product> store = ProductStore.getInstance();
    private ValidationService validationService = new ValidationServiceImp();

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
        Product entity = new Product();
        entity.setId(Long.valueOf(id));
        if (store.delete(entity)) {
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
