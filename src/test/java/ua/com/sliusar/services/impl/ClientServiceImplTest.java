package ua.com.sliusar.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.sliusar.dao.ClientDao;
import ua.com.sliusar.domain.Client;
import ua.com.sliusar.services.ClientService;
import ua.com.sliusar.validators.ValidationService;
import ua.com.sliusar.validators.impl.ValidationServiceImp;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

/**
 * Class ClientServiceImplTest
 *
 * @author create by ivanslusar
 * 3/6/19
 * @project MyLuxoftProject
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {
    @Mock
    ClientDao clientDao;
    ClientService clientService;
    ValidationService validationService = new ValidationServiceImp();
    public Client client1;
    public Client client2;

    @Before
    public void init() {
        this.clientService = new ClientServiceImpl(clientDao, validationService);
        this.client1 = new Client(1L, "Thomas", "Jefferson", "+380974445555", "testThomas@gmail.com", 50);
        this.client2 = new Client(2L, "Jimmy", "Carter", "+380504445551", "testJimmy@gmail.com", 50);
    }

    @Test
    public void createClient() {
        clientService.createClient("Thomas", "Jefferson", "+380974445555", "testThomas@gmail.com", 50);
        client1.setId(null);
        Mockito.verify(clientDao, times(1)).createOrUpdate(client1);
        Mockito.verifyNoMoreInteractions(clientDao);
    }

    @Test
    public void update() {
        Mockito
                .when(clientDao.findById(1L))
                .thenReturn(client1);
        Mockito
                .when(clientDao.createOrUpdate(client1))
                .thenReturn(true);

        clientService.update("1",
                new HashMap<String, String>() {
                    {
                        put("name", "Dude");
                    }
                });
        Mockito.verify(clientDao, times(1)).createOrUpdate(client1);
        assertTrue("Dude".equals(client1.getName()));
    }

    @Test
    public void delete() {
        Mockito
                .when(clientDao.delete(1L))
                .thenReturn(true);
        clientService.delete("1");
        Mockito.verify(clientDao, times(1)).delete(1l);
        assertNull(clientService.findById("1"));
    }

    @Test
    public void findById() {
        Mockito
                .when(clientDao.findById(1L))
                .thenReturn(client1);
        Client findClient = clientService.findById("1");
        assertEquals(client1, findClient);
    }

    @Test
    public void findAll() {
        Mockito
                .when(clientDao.findAll())
                .thenReturn(Arrays.asList(
                        client1, client2
                ));
        assertTrue(clientService.findAll().size() == 2);
    }
}