package ua.com.sliusar.services.impl;

import org.springframework.stereotype.Service;
import ua.com.sliusar.domain.Order;
import ua.com.sliusar.persistent.OrderStore;
import ua.com.sliusar.persistent.Store;
import ua.com.sliusar.services.OrderService;

import java.util.List;

/**
 * Class OrderServiceImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Store<Order> store = OrderStore.getInstance();

    public OrderServiceImpl() {
    }

    @Override
    public void update(Order order) {
        store.update(order);
    }

    @Override
    public void create(Order order) {
        store.add(order);
    }

    @Override
    public void delete(String id) {
        Order order = new Order();
        order.setId(Long.valueOf(id));
        store.delete(order);
    }

    @Override
    public Order findById(String id) {
        return store.findById(Long.valueOf(id));
    }

    @Override
    public List<Order> findAll() {
        return store.findAll();
    }
}
