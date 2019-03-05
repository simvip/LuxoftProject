package ua.com.sliusar.dao.impl.inMemory;

import ua.com.sliusar.dao.ProductDao;
import ua.com.sliusar.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class ProductDaoInMemoryImpl
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public class ProductDaoInMemoryImpl implements ProductDao {
    private final Map<Long, Product> productMap;
    private long count = 0;

    public ProductDaoInMemoryImpl() {
        this.productMap = new HashMap<>();
    }

    @Override
    public boolean createOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setId(++count);
        }
        productMap.put(product.getId(), product);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return productMap.remove(id) != null;
    }

    @Override
    public Product findById(Long id) {
        return productMap.get(id);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(this.productMap.values());
    }
}
