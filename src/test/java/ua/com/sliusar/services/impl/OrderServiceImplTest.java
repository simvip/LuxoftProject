package ua.com.sliusar.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.sliusar.dao.OrderDao;
import ua.com.sliusar.domain.Client;
import ua.com.sliusar.domain.Order;
import ua.com.sliusar.domain.Product;
import ua.com.sliusar.services.ClientService;
import ua.com.sliusar.services.OrderService;
import ua.com.sliusar.services.ProductService;
import ua.com.sliusar.validators.ValidationService;
import ua.com.sliusar.validators.impl.ValidationServiceImp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

/**
 * Class OrderServiceImplTest
 *
 * @author create by ivanslusar
 * 3/15/19
 * @project MyLuxoftProject
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    @Mock
    private OrderDao orderDAO;
    @Mock
    private ProductService productService;
    @Mock
    private ClientService clientService;

    private ValidationService validationService = new ValidationServiceImp();
    private OrderService orderService;
    private Product product1;
    private Product product2;
    private Client client1;
    private Order order1;

    @Before
    public void initTest() {
//        this.orderService = new OrderServiceImpl(orderDAO, productService, clientService, validationService);
        this.product1 = new Product(1L, "Cocaine", BigDecimal.TEN);
        this.product2 = new Product(2L, "Chocolate", BigDecimal.ONE);
        this.client1 = new Client(1L, "Thomas", "Jefferson", "+380974445555", "testThomas@gmail.com", 50);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        this.order1 = new Order(1L,BigDecimal.TEN, client1, productList);
    }

    @Test
    public void create() {
        Mockito
                .when(productService.findById("1"))
                .thenReturn(product1);
        Mockito
                .when(clientService.findById("1"))
                .thenReturn(client1);
//        orderService.create("1", "10", "1");
        order1.setId(null);
        Mockito.verify(orderDAO, times(1)).createOrUpdate(order1);
    }

    @Test
    public void update() {
        Mockito
                .when(productService.findById("2"))
                .thenReturn(product2);
        Mockito
                .when(orderService.findById("1"))
                .thenReturn(order1);
//        orderService.update("1",new HashMap<String, String>(){{
//            put("idProductAddToOrder","2");
//        }});
       assertEquals(order1.getTotalPrice().intValue(),11);
    }

    @Test
    public void delete() { Mockito
            .when(orderDAO.delete(1L))
            .thenReturn(true);
        orderService.delete("1");
        Mockito.verify(orderDAO, times(1)).delete(1l);
        assertNull(orderService.findById("1"));
    }

    @Test
    public void findById() {
        Mockito
                .when(orderDAO.findById(1L))
                .thenReturn(order1);
        Order findOrder = orderService.findById("1");
        assertEquals(order1, findOrder);
    }

    @Test
    public void findAll() {
        Mockito
                .when(orderDAO.findAll())
                .thenReturn(Arrays.asList(
                        order1
                ));
        assertTrue(orderService.findAll().size() == 1);
    }
}