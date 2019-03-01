package ua.com.sliusar.services;

import ua.com.sliusar.domain.Order;

/**
 * Class OrderService
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public interface OrderService extends CrudService<Order> {
    void create(String clientID, String productId);
}
