package ua.com.sliusar.dao.impl;

import ua.com.sliusar.dao.ClientDAO;
import ua.com.sliusar.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class ClientDAOInMemoryImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class ClientDAOInMemoryImpl implements ClientDAO {
    private final Map<Double, Client> clientMap;

    public ClientDAOInMemoryImpl() {
        this.clientMap = new HashMap<>();
    }

    @Override
    public boolean saveClient(Client client) {
        if (client.getId() == null) {
            client.setId((double) (this.clientMap.values().size() + 1));
        }
        clientMap.put(client.getId(), client);
        return findClient(client.getId()) != null;
    }

    @Override
    public boolean delete(Double id) {
        return clientMap.remove(id) != null;
    }

    @Override
    public Client findClient(Double id) {
        return clientMap.get(id);
    }

    @Override
    public List<Client> findAllClients() {
        return new ArrayList<>(this.clientMap.values());
    }
}
