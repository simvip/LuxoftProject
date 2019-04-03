package ua.com.sliusar.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.sliusar.domain.Client;
import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.persistent.Store;
import ua.com.sliusar.services.ClientService;
import ua.com.sliusar.validators.ValidationService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class ClientServiceImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    private Store<Client> store;
    @Autowired
    private ValidationService validationService;

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
        if (store.delete(Long.valueOf(id))) {
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
