package ua.com.sliusar.services.impl;

import ua.com.sliusar.dao.ProductDao;
import ua.com.sliusar.domain.Product;
import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.services.ProductService;
import ua.com.sliusar.validators.ValidationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Class ProductServiceImpl
 *
 * @author create by ivanslusar
 * 2/15/19
 */
public class ProductServiceImpl implements ProductService {
    private ProductDao productDAO;
    private ValidationService validationService;

    public ProductServiceImpl(ProductDao productDAO, ValidationService validationService) {
        this.productDAO = productDAO;
        this.validationService = validationService;
    }

    @Override
    public void create(String name, String price) {
        try {
            validationService.validateBigDecimal(price);
            Product product = new Product(name, new BigDecimal(price));
            if (productDAO.createOrUpdate(product)){
                System.out.println("Product was success created");
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String id, Map<String, String> updateFields) {
        Product product = productDAO.findById(Long.valueOf(id));
        if (product == null) {
            System.out.println("Product with such id " + id + " doesn`t find");
            return;
        }
        for (Map.Entry<String, String> pair : updateFields.entrySet()) {
            switch (pair.getKey()) {
                case "name":
                    product.setName(pair.getValue());
                    break;
                case "price":
                    String price = pair.getValue();
                    try {
                        validationService.validateBigDecimal(price);
                        product.setPrice(new BigDecimal(price));
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        if (productDAO.createOrUpdate(product)){
            System.out.println("Product was successes updated");
        } else {
            System.out.println("Product was crushed");
        }
    }

    @Override
    public void delete(String id) {
        if (productDAO.delete(Long.valueOf(id))){
            System.out.println("Product was successes deleted");
        } else {
            System.out.println("Product wasn`t deleted");
        }
    }

    @Override
    public Product findById(String id) {
        return productDAO.findById(Long.valueOf(id));
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

}
