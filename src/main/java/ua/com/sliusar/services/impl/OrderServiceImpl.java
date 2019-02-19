package ua.com.sliusar.services.impl;

import ua.com.sliusar.dao.OrderDAO;
import ua.com.sliusar.domain.Order;
import ua.com.sliusar.services.OrderService;
import ua.com.sliusar.validators.ValidationService;

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
    private OrderDAO orderDAO;
    private ValidationService validationService;

    public OrderServiceImpl(OrderDAO orderDAO, ValidationService validationService) {
        this.orderDAO = orderDAO;
        this.validationService = validationService;
    }

    @Override
    public void create(String name, String price) {
        Order order = new Order();
        if (orderDAO.createOrUpdate(order)) {
            System.out.println("Order was success created");
        }
    }

    @Override
    public void update(String id, Map<String, String> updateFields) {
        Order order = orderDAO.findById(Double.valueOf(id));
        if (order == null) {
            System.out.println("Order with such id " + id + " doesn`t find");
            return;
        }
        for (Map.Entry<String, String> pair : updateFields.entrySet()) {
            switch (pair.getKey()) {
                case "name":
//                    order.setName(pair.getValue());
                    break;
                case "price":
//                    order.setPhone(pair.getValue());
                    break;
            }
        }
//        if (orderDAO.createOrUpdate(client)) {
//            System.out.println("Client was successes updated");
//        } else {
//            System.out.println("Update was crushed");
//        }
    }

    @Override
    public void delete(String id) {
        if (orderDAO.delete(Double.valueOf(id))) {
            System.out.println("Order was successes deleted");
        } else {
            System.out.println("Order wasn`t deleted");
        }
    }

    @Override
    public Order findById(String id) {
        return orderDAO.findById(Double.valueOf(id));
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }
}
