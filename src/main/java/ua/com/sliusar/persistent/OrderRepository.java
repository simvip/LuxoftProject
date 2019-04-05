package ua.com.sliusar.persistent;

import org.springframework.data.repository.CrudRepository;
import ua.com.sliusar.domain.Order;

/**
 * Class OrderRepository
 *
 * @author create by ivanslusar
 * 4/3/19
 * @project MyLuxoftProject
 */
public interface OrderRepository extends CrudRepository<Order,Long> {
}
