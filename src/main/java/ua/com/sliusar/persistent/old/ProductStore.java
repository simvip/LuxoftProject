package ua.com.sliusar.persistent.old;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.sliusar.domain.Product;
import ua.com.sliusar.util.UtilHibernate;

import javax.persistence.Query;
import java.util.List;
import java.util.function.Function;

/**
 * Class ProductStore
 *
 * @author create by ivanslusar
 * 3/29/19
 * @project MyLuxoftProject
 */

public class ProductStore implements Store<Product> {
    private static final Logger LOGGER = Logger.getLogger(ClientStore.class);

    public ProductStore() {
    }

    @Override
    public void add(Product entity) {
        this.tx(session -> session.save(entity));
    }

    @Override
    public void update(Product entity) {
        this.tx(session -> {
            session.update(entity);
            return true;
        });
    }

    @Override
    public boolean delete(long id) {
        return this.tx(session -> {
            final Query query = session.createQuery("delete from Product where id=:id");
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        });
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) this.tx(
                session -> session.createQuery("from Product ").list()
        );
    }

    @Override
    public Product findById(long id) {
        return this.tx(session -> session.get(Product.class, id));
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = UtilHibernate.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            LOGGER.error(e);
            throw e;
        } finally {
            transaction.commit();
            session.close();
        }
    }
}
