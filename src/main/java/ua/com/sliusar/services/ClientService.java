package ua.com.sliusar.services;

import ua.com.sliusar.domain.Client;

/**
 * Class ClientService
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public interface ClientService extends CrudService<Client>{
    /**
     * Method creates client by a number of parameters: name,surname and phone.
     *
     * @param name    String
     * @param surname String
     * @param phone   String
     */
    void createClient(String name, String surname, String phone);
    void createClient(String name, String surname, String phone, String email, int age);
}