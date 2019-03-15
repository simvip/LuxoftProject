package ua.com.sliusar.dao.impl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.sliusar.dao.ClientDao;
import ua.com.sliusar.dao.impl.inMemory.ClientDaoInMemoryImpl;
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

    @BeforeClass
    public static void setUp() {
        dao = ClientDaoInMemoryImpl.getInstance();
    }

    @Before
    public void initial() {
        this.client1 = new Client(1L, "Thomas", "'Jefferson'", "+380974445555", "testThomas@gmail.com", 50);
        this.client2 = new Client(2L, "Jimmy", "Carter", "+380504445551", "testJimmy@gmail.com", 50);
    }

    @Test
    public void testAddClient() {
        assertTrue(dao.createOrUpdate(client2));
    }

    @Test
    public void testUpdateClient() {
        dao.createOrUpdate(client1);
        client1.setName("Dude");
        Client findClient = dao.findById(1L);
        assertTrue("Dude".equals(findClient.getName()));
    }

    @Test
    public void testGetAllClients() {
        dao.createOrUpdate(client1);
        dao.createOrUpdate(client2);
        assertTrue(dao.findAll().size() == 2);
    }

    @Test
    public void testDeleteClient() {
        dao.createOrUpdate(client1);
        assertTrue(dao.delete(1L));
        assertTrue(dao.findById(1L) == null);
    }
}