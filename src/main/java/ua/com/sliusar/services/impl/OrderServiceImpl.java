package ua.com.sliusar.services.impl;

import ua.com.sliusar.dao.OrderDao;
import ua.com.sliusar.domain.Order;
import ua.com.sliusar.domain.Product;
import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.services.ClientService;
import ua.com.sliusar.services.OrderService;
import ua.com.sliusar.services.ProductService;
import ua.com.sliusar.validators.ValidationService;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private ClientService clientService;
    private ValidationService validationService;

    public OrderServiceImpl(OrderDao orderDAO, ProductService productService, ClientService clientService, ValidationService validationService) {
        this.orderDAO = orderDAO;
        this.productService = productService;
        this.clientService = clientService;
        this.validationService = validationService;
    }

    @Override
    public void create(String clientId, String price, String productId) {
        Product addProduct = productService.findById(productId);
        List<Product> productList = new ArrayList<>();
        productList.add(addProduct);
        Order order = new Order(
                addProduct.getPrice(),
                clientService.findById(clientId),
                productList
        );
        try {
            validationService.validateBigDecimal(price);
            order.setTotalPrice(new BigDecimal(price));
        } catch (BusinessException e) {
            e.printStackTrace();
        }
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
                    order.setClient(clientService.findById(pair.getValue()));
                    break;
                case "idProductAddToOrder":
                    Product foundProduct = productService.findById(pair.getValue());
                    order.setTotalPrice(
                            order.getTotalPrice().add(foundProduct.getPrice())
                    );
                    if (foundProduct == null) {
                        System.out.println("Was not found Product with such ID");
                    } else {
                        List<Product> productList = order.getProductList();
                        productList.add(foundProduct);
                        order.setProductList(productList);
                    }
                    break;
                case "idProductDeleteFromOrder":
                    Product foundProduct2 = productService.findById(pair.getValue());
                    if (foundProduct2 == null) {
                        System.out.println("Was not found Product with such ID");
                    } else {
                        order.getProductList().remove(foundProduct2);
                    }
                    break;
            }
        }
        orderDAO.createOrUpdate(order);
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
