package ua.com.sliusar.dao.impl;

import ua.com.sliusar.dao.ClientDao;
import ua.com.sliusar.domain.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Class ClientDBDao
 *
 * @author create by ivanslusar
 * 2/27/19
 * @project MyLuxoftProject
 */
public class ClientDBDao implements ClientDao {
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/DB/WorkBase";
    public static final String USER = "sa";
    public static final String PASSWORD = "";

    private Connection connection;

    public ClientDBDao() {
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
    public boolean createOrUpdate(Client value) {
        String query = "INSERT INTO CLIENT(NAME,SURNAME,EMAIL,CREATEDATE) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, value.getName());
            stmt.setString(2, value.getName());
            stmt.setString(3, value.getEmail());
//            stmt.setDate(4, new Date(user.getCreateDate().getTime()));
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Client findById(Long id) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }
}
