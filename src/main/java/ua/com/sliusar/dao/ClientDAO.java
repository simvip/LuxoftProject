package ua.com.sliusar.dao;

import ua.com.sliusar.domain.Client;

import java.util.List;

/**
 * Interface ClientDAO
 *
 * @author create by ivanslusar
 * 2/13/19
 * @project MyLuxoftProject
 */
public interface ClientDAO {
    /**
     * Method add or update client in DB.
     *
     * @param client Client
     * @return Boolean
     */
    boolean saveOrUpdateClient(Client client);

    /**
     * Method delete client in DB by id.
     *
     * @param id Long
     * @return Boolean
     */
    boolean delete(long id);

    /**
     * Method finds client in DB and returns Client if it finds one by id
     * or returns null in case if not find.
     *
     * @param id
     * @return
     */
    Client findClient(long id);

    /**
     * Method finds All clients in DB.
     *
     * @return List<Client>
     */
    List<Client> findAllClients();
}
