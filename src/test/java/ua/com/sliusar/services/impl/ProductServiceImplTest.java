package ua.com.sliusar.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.sliusar.dao.ProductDao;
import ua.com.sliusar.domain.Product;
import ua.com.sliusar.services.ProductService;
import ua.com.sliusar.validators.ValidationService;
import ua.com.sliusar.validators.impl.ValidationServiceImp;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

/**
 * Class ProductServiceImplTest
 *
 * @author create by ivanslusar
 * 3/15/19
 * @project MyLuxoftProject
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @Mock
    ProductDao productDao;
    ValidationService validationService = new ValidationServiceImp();
    ProductService productService;
    Product product1;
    Product product2;


    @Before
    public void init() {
        this.productService = new ProductServiceImpl(productDao, validationService);
        this.product1 = new Product(1L, "Cocaine", BigDecimal.TEN);
        this.product2 = new Product(2L, "Chocolate", BigDecimal.ZERO);
    }

    @Test
    public void create() {
        productService.create("Cocaine", "10");
        product1.setId(null);
        Mockito.verify(productDao, times(1)).createOrUpdate(product1);
    }

    @Test
    public void update() {
        Mockito
                .when(productDao.findById(1L))
                .thenReturn(product1);
        Mockito
                .when(productDao.createOrUpdate(product1))
                .thenReturn(true);
        productService.update("1", new HashMap<String, String>() {
            {
                put("price", "1");
            }
        });
        Mockito.verify(productDao, times(1)).createOrUpdate(product1);
        assertEquals(BigDecimal.ONE, product1.getPrice());
    }

    @Test
    public void delete() {
        Mockito
                .when(productDao.delete(1L))
                .thenReturn(true);
        productService.delete("1");
        Mockito.verify(productDao, times(1)).delete(1l);
        assertNull(productService.findById("1"));
    }

    @Test
    public void findById() {
        Mockito
                .when(productDao.findById(1L))
                .thenReturn(product1);
        Product findProduct = productService.findById("1");
        assertEquals(product1, findProduct);
    }

    @Test
    public void findAll() {
        Mockito
                .when(productDao.findAll())
                .thenReturn(Arrays.asList(
                        product1, product2
                ));
        assertTrue(productService.findAll().size() == 2);
    }
}