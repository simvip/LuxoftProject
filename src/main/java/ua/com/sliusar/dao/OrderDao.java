package ua.com.sliusar.dao;

import ua.com.sliusar.domain.Order;

import java.util.List;

/**
 * Class OrderDao
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public interface OrderDao extends CrudDao<Order> {

    /**
     * Method finds Clients orders.
     *
     * @param clientID Long
     * @return List
     */
    List<Order> findAllOrderOfClient(Long clientID);

}
