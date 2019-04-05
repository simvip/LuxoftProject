package ua.com.sliusar.persistent;

import org.springframework.data.repository.CrudRepository;
import ua.com.sliusar.domain.Client;

/**
 * Class ClientRepository
 *
 * @author create by ivanslusar
 * 4/3/19
 * @project MyLuxoftProject
 */
public interface ClientRepository extends CrudRepository<Client,Long> {
}
