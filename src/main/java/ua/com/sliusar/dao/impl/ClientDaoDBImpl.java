package ua.com.sliusar.dao.impl;

import ua.com.sliusar.dao.ClientDao;
import ua.com.sliusar.domain.Client;

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
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/JavaProjects/MyLuxoftProject/src/main/resources/DB/WorkBase";
    public static final String USER = "sa";
    public static final String PASSWORD = "";

    private Connection connection;

    public ClientDaoDBImpl() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class. No driver found");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }

    }

    @Override
    public boolean createOrUpdate(Client client) {
        if (client.getId()==null)
            return create(client);
        else
            return update(client);
    }

    private boolean update(Client client) {
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
        String query = "SELECT * FROM CLIENT WHERE CLIENT.ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    return new Client(
                            rs.getLong("Id"),
                            rs.getString("Name"),
                            rs.getString("Surname"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getInt("age")
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
        String query = "SELECT * FROM CLIENT WHERE CLIENT.PHONE = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, phoneNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    return new Client(
                            rs.getLong("Id"),
                            rs.getString("Name"),
                            rs.getString("Surname"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getInt("age")
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
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENT;")) {
            ArrayList rezult = new ArrayList();
            while (rs.next()) {
                rezult.add(
                        new Client(
                                rs.getString("Name"),
                                rs.getString("Surname"),
                                rs.getString("phone"),
                                rs.getString("email"),
                                rs.getInt("age")
                        ));
            }

            return rezult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
