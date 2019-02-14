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
    public boolean createClient(String name, String surname, String phone) {
        Client client = new Client(name, surname, phone);
        return clientDAO.saveOrUpdateClient(client);
    }

    @Override
    public boolean updateClient(String id, Map<String, String> updateFields) {
        client = clientDAO.findClient(Integer.valueOf(id));
        if (client == null) {
            return false;
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
        clientDAO.saveOrUpdateClient(client);
        return true;
    }

    @Override
    public boolean deleteClient(String id) {
        clientDAO.delete(Long.valueOf(id));
        return clientDAO.findClient(Long.valueOf(id)) != null;
    }

    @Override
    public Client findClient(String id) {
        clientDAO.findClient(Integer.valueOf(id));
        return null;
    }

    @Override
    public List<Client> findAll() {
        return clientDAO.findAllClients();
    }
}
