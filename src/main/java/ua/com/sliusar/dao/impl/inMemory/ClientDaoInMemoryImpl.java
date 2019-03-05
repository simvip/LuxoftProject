package ua.com.sliusar.dao.impl.inMemory;

import ua.com.sliusar.dao.ClientDao;
import ua.com.sliusar.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class ClientDaoInMemoryImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class ClientDaoInMemoryImpl implements ClientDao{
    private final Map<Long, Client> clientMap = new HashMap<>();
    private long count = 0;
    private static volatile ClientDaoInMemoryImpl instance;

    public static ClientDaoInMemoryImpl getInstance() {
        ClientDaoInMemoryImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (ClientDaoInMemoryImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ClientDaoInMemoryImpl();
                }
            }
        }
        return localInstance;
    }

    private ClientDaoInMemoryImpl() {

    }

    @Override
    public boolean createOrUpdate(Client client) {
        if (client.getId() == null) {
            client.setId((++this.count));
        }
        clientMap.put(client.getId(), client);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return clientMap.remove(id) != null;
    }

    @Override
    public Client findById(Long id) {
        return clientMap.get(id);
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(this.clientMap.values());
    }

    @Override
    public Client findByPhone(String phoneNumber) {
        return null;
    }
}
