package ua.com.sliusar.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.sliusar.domain.Order;
import ua.com.sliusar.persistent.Store;
import ua.com.sliusar.services.OrderService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class OrderServiceImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private Store<Order> store;

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
        store.delete(Long.valueOf(id));
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
