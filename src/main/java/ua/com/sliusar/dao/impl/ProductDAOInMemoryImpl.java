package ua.com.sliusar.dao.impl;

import ua.com.sliusar.dao.ProductDAO;
import ua.com.sliusar.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class ProductDAOInMemoryImpl
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public class ProductDAOInMemoryImpl implements ProductDAO {
    private final Map<Double, Product> productMap;

    public ProductDAOInMemoryImpl() {
        this.productMap = new HashMap<>();
    }

    @Override
    public boolean createOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setId((double) (this.productMap.values().size() + 1));
        }
        productMap.put(product.getId(), product);
        return findById(product.getId()) != null;
    }

    @Override
    public boolean delete(Double id) {
        return productMap.remove(id) != null;
    }

    @Override
    public Product findById(Double id) {
        return productMap.get(id);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(this.productMap.values());
    }
}
