package ua.com.sliusar.dao.impl;

import ua.com.sliusar.dao.ClientDAO;
import ua.com.sliusar.domain.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class ClientDAOInMemoryImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class ClientDAOInMemoryImpl implements ClientDAO {
    private final Map<Long, Client> clientMap;

    public ClientDAOInMemoryImpl() {
        this.clientMap = new ConcurrentHashMap<>();
    }

    public boolean saveOrUpdateClient(Client client) {
        if (client.getId() == 0) {
            client.setId(this.clientMap.values().size() + 1);
        }
        clientMap.put(client.getId(), client);
        return findClient(client.getId()) != null;
    }

    @Override
    public boolean delete(long id) {
        if (clientMap.containsKey(id)) {
            clientMap.remove(id);
            System.out.println("Client delete");
            return true;
        }
        return false;
    }

    @Override
    public Client findClient(long id) {
        if (clientMap.containsKey(id)) {
            return clientMap.get(id);
        }
        return null;
    }

    @Override
    public List<Client> findAllClients() {
        return new ArrayList<>(this.clientMap.values());
    }
}
