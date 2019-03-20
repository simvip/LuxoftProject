package ua.com.sliusar.services.impl;

import ua.com.sliusar.dao.ClientDao;
import ua.com.sliusar.domain.Client;
import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.services.ClientService;
import ua.com.sliusar.validators.ValidationService;

import java.util.List;
import java.util.Map;

/**
 * Class ClientServiceImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class ClientServiceImpl implements ClientService {
    private ClientDao clientDAO;
    private ValidationService validationService;

    public ClientServiceImpl(ClientDao clientDAO, ValidationService validationService) {
        this.clientDAO = clientDAO;
        this.validationService = validationService;
    }


    @Override
    public void createClient(String name, String surname, String phone, String email, int age) {
        try {
            validationService.validateAge(age);
            validationService.validateEmail(email);
            validationService.validatePhone(phone);
            Client client = new Client(name, surname, phone, email, age);
            if (clientDAO.createOrUpdate(client)) {
                System.out.println("Client was success created");
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createClient(String name, String surname, String phone) {
        createClient(name, surname, phone, "", 0);
    }

    @Override
    public void update(String id, Map<String, String> updateFields) {
        Client client = clientDAO.findById(Long.valueOf(id));
        if (client == null) {
            System.out.println("Client with such id " + id + " doesn`t find");
            return;
        }
        for (Map.Entry<String, String> pair : updateFields.entrySet()) {
            switch (pair.getKey()) {
                case "name":
                    client.setName(pair.getValue());
                    break;
                case "phone":
                    client.setPhone(pair.getValue());
                    break;
            }
        }
        if (clientDAO.createOrUpdate(client)) {
            System.out.println("Client was successes updated");
        } else {
            System.out.println("Update was crushed");
        }
    }

    @Override
    public void delete(String id) {
        if (clientDAO.delete(Long.valueOf(id))) {
            System.out.println("Client was successes deleted");
        } else {
            System.out.println("Client wasn`t deleted");
        }
    }

    @Override
    public Client findById(String id) {
        return clientDAO.findById(Long.valueOf(id));
    }

    @Override
    public List<Client> findAll() {
        return clientDAO.findAll();
    }
}
