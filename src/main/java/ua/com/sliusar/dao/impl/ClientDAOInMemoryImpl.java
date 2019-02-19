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
    private static volatile ClientDAOInMemoryImpl instance;

    public static ClientDAOInMemoryImpl getInstance(){
        ClientDAOInMemoryImpl localInstance = instance;
        if (localInstance==null){
            synchronized (ClientDAOInMemoryImpl.class){
                localInstance =instance;
                if (localInstance==null){
                    instance = localInstance = new ClientDAOInMemoryImpl();
                }
            }
        }
        return localInstance;
    }

    private ClientDAOInMemoryImpl() {
        this.clientMap = new HashMap<>();
    }

    @Override
    public boolean createOrUpdate(Client client) {
        if (client.getId() == null) {
            client.setId((double) (this.clientMap.values().size() + 1));
        }
        clientMap.put(client.getId(), client);
        return findById(client.getId()) != null;
    }

    @Override
    public boolean delete(Double id) {
        return clientMap.remove(id) != null;
    }

    @Override
    public Client findById(Double id) {
        return clientMap.get(id);
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(this.clientMap.values());
    }
}
