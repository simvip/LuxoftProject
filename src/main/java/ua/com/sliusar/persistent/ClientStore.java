package ua.com.sliusar.persistent;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.sliusar.domain.Client;
import ua.com.sliusar.util.UtilHibernate;

import javax.persistence.Query;
import java.util.List;
import java.util.function.Function;

/**
 * Class ClientStore
 *
 * @author create by ivanslusar
 * 3/25/19
 * @project MyLuxoftProject
 */
public class ClientStore implements Store<Client> {
    private static final Logger LOGGER = Logger.getLogger(ClientStore.class);
    final static ClientStore INSTANCE = new ClientStore();

    public static Store getInstance() {
        return INSTANCE;
    }

    public ClientStore() {
    }

    @Override
    public void add(Client entity) {
        this.tx(session -> session.save(entity));
    }

    @Override
    public void update(Client entity) {
        this.tx(session -> {
            session.update(entity);
            return true;
        });
    }

    @Override
    public boolean delete(Client entity) {
        return this.tx(session -> {
            final Query query = session.createQuery("delete from Client where id=:id");
            query.setParameter("id", entity.getId());
            query.executeUpdate();
            return true;
        });
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) this.tx(
                session -> session.createQuery("from Client ").list()
        );
    }

    @Override
    public Client findById(long id) {
        return this.tx(session -> session.get(Client.class, id));
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
