package ua.com.sliusar.dao.impl;

import ua.com.sliusar.dao.ProductDao;
import ua.com.sliusar.domain.Product;

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
public class ProductDaoDBImpl implements ProductDao {
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/JavaProjects/MyLuxoftProject/src/main/resources/DB/WorkBase";
    public static final String USER = "sa";
    public static final String PASSWORD = "";

    private Connection connection;

    public ProductDaoDBImpl() {
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
    public boolean createOrUpdate(Product product) {
        if (product.getId() == null)
            return create(product);
        else
            return update(product);
    }

    private boolean update(Product product) {
        String query = "UPDATE PRODUCT SET NAME = ?,PRICE = ?  WHERE PRODUCT.ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setBigDecimal(2, product.getPrice());
            stmt.setLong(3, product.getId());
            stmt.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean create(Product product) {
        String query = "INSERT INTO PRODUCT(NAME,PRICE) VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setBigDecimal(2, product.getPrice());
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
        String query = "DELETE FROM PRODUCT WHERE PRODUCT.ID = ?";
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
    public Product findById(Long id) {
        String query = "SELECT * FROM PRODUCT WHERE PRODUCT.ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    return new Product(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getBigDecimal("price")

                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Product> findAll() {
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT;")) {
            ArrayList rezult = new ArrayList();
            while (rs.next()) {
                rezult.add(
                        new Product(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")

                        ));
            }

            return rezult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
