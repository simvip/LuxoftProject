package ua.com.sliusar.services;

import java.util.List;
import java.util.Map;

/**
 * Class OrderService
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public interface CrudService<T> {

    void delete(String id);
    void update(String id, Map<String, String> updateFields);
    T findById(String id);
    List<T> findAll();
}
