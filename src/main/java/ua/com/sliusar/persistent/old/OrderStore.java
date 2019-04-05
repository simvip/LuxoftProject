package ua.com.sliusar.persistent.old;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.sliusar.domain.Order;
import ua.com.sliusar.util.UtilHibernate;

import javax.persistence.Query;
import java.util.List;
import java.util.function.Function;

/**
 * Class OrderStore
 *
 * @author create by ivanslusar
 * 3/29/19
 * @project MyLuxoftProject
 */

public class OrderStore implements Store<Order> {
    private static final Logger LOGGER = Logger.getLogger(OrderStore.class);

    public OrderStore() {
    }

    @Override
    public void add(Order entity) {
        this.tx(session -> session.save(entity));
    }

    @Override
    public void update(Order entity) {
        this.tx(session -> {
            session.update(entity);
            return true;
        });
    }

    @Override
    public boolean delete(long id) {
        return this.tx(session -> {
            final Query query = session.createQuery("delete from Order where id=:id");
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        });
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) this.tx(
                session -> session.createQuery("from Order ").list()
        );
    }


    @Override
    public Order findById(long id) {
        return this.tx(session -> session.get(Order.class, id));
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
