package ua.com.sliusar.services.impl;

import ua.com.sliusar.dao.ClientDAO;
import ua.com.sliusar.dao.impl.ClientDAOInMemoryImpl;
import ua.com.sliusar.domain.Client;
import ua.com.sliusar.services.ClientService;

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
    private ClientDAO clientDAO = new ClientDAOInMemoryImpl();
    private Client client;

    @Override
    public void createClient(String name, String surname, String phone) {
        Client client = new Client(name, surname, phone);
        if (clientDAO.saveClient(client)){
            System.out.println("Client was success created");
        }
    }

    @Override
    public void updateClient(String id, Map<String, String> updateFields) {
        client = clientDAO.findClient(Double.valueOf(id));
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
        if (clientDAO.saveClient(client)){
            System.out.println("Client was successes updated");
        } else {
            System.out.println("Update was crushed");
        }
    }

    @Override
    public void deleteClient(String id) {
        if (clientDAO.delete(Double.valueOf(id))){
            System.out.println("Client was successes deleted");
        } else {
            System.out.println("Client wasn`t deleted");
        }
    }

    @Override
    public Client findClient(String id) {
        return clientDAO.findClient(Double.valueOf(id));
    }

    @Override
    public List<Client> findAll() {
        return clientDAO.findAllClients();
    }
}
