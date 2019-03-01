package ua.com.sliusar.dao.impl;

import ua.com.sliusar.dao.OrderDao;
import ua.com.sliusar.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class OrderDaoInMemoryImpl
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public class OrderDaoInMemoryImpl implements OrderDao {
    private final Map<Long, Order> orderMap;
    private long count = 0;

    public OrderDaoInMemoryImpl() {
        this.orderMap = new HashMap<>();
    }

    @Override
    public boolean createOrUpdate(Order order) {
        if (order.getId() == null) {
            order.setId(++count);
        }
        orderMap.put(order.getId(), order);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return orderMap.remove(id) != null;
    }

    @Override
    public Order findById(Long id) {
        return orderMap.get(id);
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(this.orderMap.values());
    }

    @Override
    public List<Order> findAllOrderOfClient(Long clientID) {
        return orderMap.values().stream()
                .filter(client -> client.getClientID().equals(clientID))
                .collect(Collectors.toList());
    }
}
