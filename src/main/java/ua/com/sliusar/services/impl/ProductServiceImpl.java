package ua.com.sliusar.services.impl;

import ua.com.sliusar.dao.ProductDAO;
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
 * @project MyLuxoftProject
 */
public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    private ValidationService validationService;

    public ProductServiceImpl(ProductDAO productDAO, ValidationService validationService) {
        this.productDAO = productDAO;
        this.validationService = validationService;
    }

    @Override
    public void create(String name, String price) {
        try {
            validationService.validateBigВecimal(price);
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
        Product product = productDAO.findById(Double.valueOf(id));
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
                    product.setPrice(new BigDecimal(pair.getValue()));
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
        if (productDAO.delete(Double.valueOf(id))){
            System.out.println("Product was successes deleted");
        } else {
            System.out.println("Product wasn`t deleted");
        }
    }

    @Override
    public Product findById(String id) {
        return productDAO.findById(Double.valueOf(id));
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

}
