package ua.com.sliusar.persistent;

import org.springframework.data.repository.CrudRepository;
import ua.com.sliusar.domain.Product;

/**
 * Class ProductRepository
 *
 * @author create by ivanslusar
 * 4/3/19
 * @project MyLuxoftProject
 */
public interface ProductRepository extends CrudRepository<Product,Long> {
}
