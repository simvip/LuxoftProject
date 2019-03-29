package ua.com.sliusar.services.impl;

import org.springframework.stereotype.Service;
import ua.com.sliusar.domain.Client;
import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.persistent.ClientStore;
import ua.com.sliusar.persistent.Store;
import ua.com.sliusar.services.ClientService;
import ua.com.sliusar.validators.ValidationService;
import ua.com.sliusar.validators.impl.ValidationServiceImp;

import java.util.List;

/**
 * Class ClientServiceImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
@Service
public class ClientServiceImpl implements ClientService {
    private static final Store<Client> store = ClientStore.getInstance();
    private static final ValidationService validationService = new ValidationServiceImp();

    public ClientServiceImpl() {
    }

    @Override
    public void create(Client client) {
        try {
            validationService.validateAge(client.getAge());
            if (client.getEmail() != null) {
                validationService.validateEmail(client.getEmail());
            }
            if (client.getPhone() != null) {
                validationService.validatePhone(client.getPhone());
            }
            store.add(client);

        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {
        store.update(client);
    }

    @Override
    public void delete(String id) {
        Client entity = new Client();
        entity.setId(Long.valueOf(id));
        if (store.delete(entity)) {
            System.out.println("Client was successes deleted");
        } else {
            System.out.println("Client wasn`t deleted");
        }
    }

    @Override
    public Client findById(String id) {
        return store.findById(Long.valueOf(id));
    }

    @Override
    public List<Client> findAll() {
        return store.findAll();
    }
}
