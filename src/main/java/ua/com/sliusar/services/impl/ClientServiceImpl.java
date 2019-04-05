package ua.com.sliusar.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.sliusar.domain.Client;
import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.persistent.ClientRepository;
import ua.com.sliusar.validators.ValidationService;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
public class ClientServiceImpl {
    @Autowired
    private ClientRepository store;
    @Autowired
    private ValidationService validationService;

    public ClientServiceImpl() {
    }

    public void create(Client client) {
        try {
            validationService.validateAge(client.getAge());
            if (client.getEmail() != null) {
                validationService.validateEmail(client.getEmail());
            }
            if (client.getPhone() != null) {
                validationService.validatePhone(client.getPhone());
            }
            store.save(client);

        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    public void update(Client client) {
        store.save(client);
    }

    public void delete(String id) {
        store.deleteById(Long.valueOf(id));
    }

    public Client findById(String id) {
        return store.findById(Long.valueOf(id)).get();
    }

    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        store.findAll().forEach(client -> clients.add(client));
        return clients;
    }
}
