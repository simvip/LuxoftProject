package ua.com.sliusar.dao;

import java.util.List;

/**
 * Class CrudDao
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public interface CrudDao<T> {
    boolean createOrUpdate(T value);

    boolean delete(Long id);

    T findById(Long id);

    List<T> findAll();
}
