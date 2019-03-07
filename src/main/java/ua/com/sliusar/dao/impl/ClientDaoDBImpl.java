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
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
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
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        return false;
    }

    private boolean create(Client client) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
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
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            String query = "DELETE FROM CLIENT WHERE CLIENT.ID = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
                connection.commit();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Client findById(Long id) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
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
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client findByPhone(String phoneNumber) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
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
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENT;")) {
                ArrayList rezult = new ArrayList();
                while (rs.next()) {
                    rezult.add(
                            new Client(
                                    rs.getLong("id"),
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
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        return null;
    }
}
