package ua.com.sliusar.dao;

import ua.com.sliusar.domain.Client;

/**
 * Interface ClientDao
 *
 * @author create by ivanslusar
 * 2/13/19
 * @project MyLuxoftProject
 */
public interface ClientDao extends CrudDao<Client> {

    Client findByPhone(String phoneNumber);

}
