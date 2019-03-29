package ua.com.sliusar.services;

import java.util.List;

/**
 * Class OrderService
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public interface CrudService<T> {

    void delete(String id);

    void update(T t);

    void create(T t);

    T findById(String id);

    List<T> findAll();
}
