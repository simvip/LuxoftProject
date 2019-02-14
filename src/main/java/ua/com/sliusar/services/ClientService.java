package ua.com.sliusar.services;

import ua.com.sliusar.domain.Client;

import java.util.List;
import java.util.Map;

/**
 * Class ClientService
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public interface ClientService {
    /**
     * Method creates client by a number of parameters: name,surname and phone.
     *
     * @param name    String
     * @param surname String
     * @param phone   String
     */
    boolean createClient(String name, String surname, String phone);

    /**
     * Method updates parameters of client.
     *
     * @param id           Long
     * @param updateFields Map
     */
    boolean updateClient(String id, Map<String, String> updateFields);

    /**
     * Method deletes client by his ID.
     *
     * @param id long
     * @return Boolean
     */
    boolean deleteClient(String id);

    /**
     * Method finds the Client in DB by his ID, if the Client will not be found
     * there is return NULL, otherwise it returns link on Client.
     *
     * @param id
     * @return
     */
    Client findClient(String id);

    /**
     * Method returns all clients from DB.
     *
     * @return List
     */
    List<Client> findAll();
}