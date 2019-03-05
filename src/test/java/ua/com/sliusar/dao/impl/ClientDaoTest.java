package ua.com.sliusar.dao.impl;

import org.junit.After;
import org.junit.Before;
import ua.com.sliusar.dao.ClientDao;
import ua.com.sliusar.domain.Client;

import static org.junit.Assert.assertTrue;

/**
 * Class ClientDaoTest
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public class ClientDaoTest {
    private static ClientDao dao;
    public Client client1;
    public Client client2;

    @Before
    public void setUp() throws Exception {
        dao = new ClientDaoDBImpl();
        client1 = new Client("Dude", "Franklin", "+380974445555");
        client2 = new Client("Stas", "Kosmos", "+380504445555");
    }

    @After
    public void tearDown() throws Exception {
        // clean DB
    }

    //@Test
    public void testAddClient() {
        assertTrue(dao.createOrUpdate(client2));
    }

    //@Test
    public void testUpdateClient() {
        dao.createOrUpdate(client1);
        Client findClient = dao.findByPhone("+380974445555");
        assertTrue(findClient.getPhone().equals(client1.getPhone()));
    }

    //@Test
    public void testGetAllClients() {
        dao.createOrUpdate(client1);
        dao.createOrUpdate(client2);
        assertTrue(dao.findAll().size()==2);
    }

    //@Test
    public void testDeleteClient() {
        dao.createOrUpdate(client1);
        Client findClient = dao.findByPhone("+380974445555");
        assertTrue(dao.delete(findClient.getId()));
    }
}