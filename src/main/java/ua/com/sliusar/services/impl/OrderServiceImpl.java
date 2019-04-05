package ua.com.sliusar.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.sliusar.domain.Order;
import ua.com.sliusar.persistent.OrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Class OrderServiceImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 */
@Service
@Transactional
public class OrderServiceImpl {
    @Autowired
    private OrderRepository store;

    public OrderServiceImpl() {
    }

    public void update(Order order) {
        store.save(order);
    }

    public void create(Order order) {
        store.save(order);
    }

    public void delete(String id) {
        Order order = new Order();
        order.setId(Long.valueOf(id));
        store.delete(order);
    }

    public Order findById(String id) {
        return store.findById(Long.valueOf(id)).get();
    }

    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        store.findAll().forEach(order -> orders.add(order));
        return orders;
    }
}
