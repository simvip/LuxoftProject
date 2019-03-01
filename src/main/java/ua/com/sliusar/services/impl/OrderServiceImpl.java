package ua.com.sliusar.services.impl;

import ua.com.sliusar.dao.OrderDao;
import ua.com.sliusar.domain.Order;
import ua.com.sliusar.domain.Product;
import ua.com.sliusar.services.OrderService;
import ua.com.sliusar.services.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class OrderServiceImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDAO;
    private ProductService productService;

    public OrderServiceImpl(OrderDao orderDAO, ProductService productService) {
        this.orderDAO = orderDAO;
        this.productService = productService;
    }

    @Override
    public void create(String clientId, String productId) {
        Order order = new Order(
                Long.valueOf(clientId),
                Arrays.asList(productService.findById(productId)
                )
        );
        if (orderDAO.createOrUpdate(order)) {
            System.out.println("Order was success created");
        }
    }

    @Override
    public void update(String id, Map<String, String> updateFields) {
        Order order = orderDAO.findById(Long.valueOf(id));
        if (order == null) {
            System.out.println("Order with such id " + id + " doesn`t find");
            return;
        }
        for (Map.Entry<String, String> pair : updateFields.entrySet()) {
            switch (pair.getKey()) {
                case "clientId":
                    order.setClientID(Long.valueOf(pair.getValue()));
                    break;
                case "idProductAddToOrder":
                    Product foundProduct = productService.findById(pair.getValue());
                    if (foundProduct == null) {
                        System.out.println("Was not found Product with such ID");
                    } else {
                        order.getProduct().add(foundProduct);
                    }
                    break;
                case "idProductDeleteFromOrder":
                    Product foundProduct2 = productService.findById(pair.getValue());
                    if (foundProduct2 == null) {
                        System.out.println("Was not found Product with such ID");
                    } else {
                        order.getProduct().remove(foundProduct2);
                    }
                    break;

            }
        }
    }

    @Override
    public void delete(String id) {
        if (orderDAO.delete(Long.valueOf(id))) {
            System.out.println("Order was successes deleted");
        } else {
            System.out.println("Order wasn`t deleted");
        }
    }

    @Override
    public Order findById(String id) {
        return orderDAO.findById(Long.valueOf(id));
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }
}
