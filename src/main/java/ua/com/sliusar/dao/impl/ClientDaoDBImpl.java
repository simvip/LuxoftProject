package ua.com.sliusar.dao.impl;

import ua.com.sliusar.dao.ClientDao;
import ua.com.sliusar.domain.Client;
import ua.com.sliusar.util.UtilJdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ClientDaoDBImpl
 *
 * @author create by ivanslusar
 * 2/27/19
 * @project MyLuxoftProject
 */
public class ClientDaoDBImpl implements ClientDao {
    private static final String ID_CLIENT = "id";
    private static final String NAME_CLIENT = "name";
    private static final String SURNAME_CLIENT = "Surname";
    private static final String PHONE_CLIENT = "phone";
    private static final String EMAIL_CLIENT = "email";
    private static final String AGE_CLIENT = "email";
    private String db_url;
    private String user;
    private String password;

    public ClientDaoDBImpl(String db_url, String user, String password) {
        this.db_url = db_url;
        this.user = user;
        this.password = password;
    }

    @Override
    public boolean createOrUpdate(Client client) {
        if (client.getId() == null) {
            return create(client);
        } else {
            return update(client);
        }
    }

    private boolean update(Client client) {
        Connection connection = UtilJdbc.getConnection(db_url, user, password);
        String query = "UPDATE CLIENT SET NAME = ?,SURNAME = ?, PHONE = ?, EMAIL = ?, AGE = ?  WHERE CLIENT.ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getSurname());
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getEmail());
            stmt.setInt(5, client.getAge());
            stmt.setLong(6, client.getId());
            stmt.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean create(Client client) {
        Connection connection = UtilJdbc.getConnection(db_url, user, password);
        String query = "INSERT INTO CLIENT(NAME,SURNAME,PHONE,EMAIL,AGE) VALUES (?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getSurname());
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getEmail());
            stmt.setInt(5, client.getAge());
            stmt.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Connection connection = UtilJdbc.getConnection(db_url, user, password);
        String query = "DELETE FROM CLIENT WHERE CLIENT.ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Client findById(Long id) {
        Connection connection = UtilJdbc.getConnection(db_url, user, password);
        String query = "SELECT * FROM CLIENT WHERE CLIENT.ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    return new Client(
                            rs.getLong(ID_CLIENT),
                            rs.getString(NAME_CLIENT),
                            rs.getString(SURNAME_CLIENT),
                            rs.getString(PHONE_CLIENT),
                            rs.getString(EMAIL_CLIENT),
                            rs.getInt(AGE_CLIENT)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client findByPhone(String phoneNumber) {
        Connection connection = UtilJdbc.getConnection(db_url, user, password);
        String query = "SELECT * FROM CLIENT WHERE CLIENT.PHONE = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, phoneNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    return new Client(
                            rs.getLong(ID_CLIENT),
                            rs.getString(NAME_CLIENT),
                            rs.getString(SURNAME_CLIENT),
                            rs.getString(PHONE_CLIENT),
                            rs.getString(EMAIL_CLIENT),
                            rs.getInt(AGE_CLIENT)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        Connection connection = UtilJdbc.getConnection(db_url, user, password);
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENT;")) {
            ArrayList rezult = new ArrayList();
            while (rs.next()) {
                rezult.add(
                        new Client(
                                rs.getLong(ID_CLIENT),
                                rs.getString(NAME_CLIENT),
                                rs.getString(SURNAME_CLIENT),
                                rs.getString(PHONE_CLIENT),
                                rs.getString(EMAIL_CLIENT),
                                rs.getInt(AGE_CLIENT)
                        ));
            }
            return rezult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
