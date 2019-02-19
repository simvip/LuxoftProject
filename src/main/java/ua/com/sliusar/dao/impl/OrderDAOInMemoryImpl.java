package ua.com.sliusar.dao.impl;

import ua.com.sliusar.dao.OrderDAO;
import ua.com.sliusar.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class OrderDAOInMemoryImpl
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public class OrderDAOInMemoryImpl implements OrderDAO {
    private final Map<Double, Order> orderMap;

    public OrderDAOInMemoryImpl() {
        this.orderMap = new HashMap<>();
    }

    @Override
    public boolean createOrUpdate(Order order) {
        if (order.getId() == null) {
            order.setId((double) (this.orderMap.values().size() + 1));
        }
        orderMap.put(order.getId(), order);
        return findById(order.getId()) != null;
    }

    @Override
    public boolean delete(Double id) {
        return orderMap.remove(id) != null;
    }

    @Override
    public Order findById(Double id) {
        return orderMap.get(id);
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(this.orderMap.values());
    }

    @Override
    public List<Order> findAllOrderOfClient(Double clientID) {
        List<Order> rezultList = new ArrayList<>();
        for (Order order : orderMap.values()) {
            if (order.getClientID() == clientID) {
                rezultList.add(order);
            }
        }
        return rezultList;
    }
}
