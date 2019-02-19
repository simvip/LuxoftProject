package ua.com.sliusar.dao;

import ua.com.sliusar.domain.Order;

import java.util.List;

/**
 * Class OrderDAO
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public interface OrderDAO extends CrudDAO<Order> {

    /**
     * Method finds Clients orders.
     *
     * @param clientID Double
     * @return List
     */
    List<Order> findAllOrderOfClient(Double clientID);

}
