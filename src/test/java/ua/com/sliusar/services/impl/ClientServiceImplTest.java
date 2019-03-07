package ua.com.sliusar.services.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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

import static net.bytebuddy.matcher.ElementMatchers.is;

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
    @Ignore
    public void init(){
        clientService = new ClientServiceImpl(clientDao,validationService);
        client1 = new Client("Dude", "Franklin", "+380974445555");
        client2 = new Client("Stas", "Kosmos", "+380504445555");
    }
    @Test
    @Ignore
    public void createClient() {
        Mockito
                .when(clientDao.findByPhone("+380974445555"))
                .thenReturn(client1);
        Client client = clientService.findById("1");
        Mockito.verify(clientDao).findById(1L);
        Mockito.verifyNoMoreInteractions(clientDao);
        Assert.assertEquals(client,is(client1));
    }


    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findAll() {
    }
}