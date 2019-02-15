package ua.com.sliusar.dao;

import org.junit.Before;
import org.junit.Test;
import ua.com.sliusar.dao.impl.ClientDAOInMemoryImpl;
import ua.com.sliusar.domain.Client;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

/**
 * Class ClientDAOTest
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public class ClientDAOTest {
    private static ClientDAO dao;
    public Client client1;
    public Client client2;

    @Before
    public void setUp() throws Exception {
        dao = new ClientDAOInMemoryImpl();
        client1 = new Client("Dude", "Franklin", "+888888888");
        client2 = new Client("Stas", "Kosmos", "+7777777");
    }

    @Test
    public void whenWeSaveClientWeExpectTrue() {
        assertTrue(dao.saveClient(client1));
    }

    @Test
    public void whenWeDeleteClientWeExpectTrue() {
        dao.saveClient(client1);
        assertTrue(dao.delete(client1.getId()));
    }

    @Test
    public void whenWeFindClientByIdWeExpectGetClientWithSameId() {
        dao.saveClient(client1);
        Client findClient = dao.findClient(client1.getId());
        assertThat(client1,is(findClient));
    }

    @Test
    public void whenWeFindAllClientsWeGetAllClientsInBase() {
        dao.saveClient(client1);
        dao.saveClient(client2);
        assertTrue(dao.findAllClients().size()==2);
    }
}