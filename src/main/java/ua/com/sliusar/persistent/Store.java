package ua.com.sliusar.persistent;

import java.util.List;

/**
 * Class Store
 *
 * @author create by ivanslusar
 * 3/25/19
 * @project MyLuxoftProject
 */
public interface Store<T> {
    void add(T entity);
    void update(T entity);
    boolean delete(T entity);
    List<T> findAll();
    T findById(long id);
}
