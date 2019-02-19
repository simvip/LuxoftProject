package ua.com.sliusar.dao;

import java.util.List;

/**
 * Class CrudDAO
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public interface CrudDAO<T> {
    boolean createOrUpdate(T value);

    boolean delete(Double id);

    T findById(Double id);

    List<T> findAll();
}
