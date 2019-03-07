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
    private String db_url;
    private String user;
    private String password;

    public ProductDaoDBImpl(String db_url, String user, String password) {
        this.db_url = db_url;
        this.user = user;
        this.password = password;
    }

    @Override
    public boolean createOrUpdate(Product product) {
        if (product.getId() == null) {
            return create(product);
        } else {
            return update(product);
        }
    }

    private boolean update(Product product) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
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
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        return false;
    }

    private boolean create(Product product) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
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
            String query = "DELETE FROM PRODUCT WHERE PRODUCT.ID = ?";
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
    public Product findById(Long id) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            String query = "SELECT * FROM PRODUCT WHERE PRODUCT.ID = ?";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        return constructProductFromResultSet(rs);
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
    public List<Product> findAll() {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT;")) {
                List result = new ArrayList();
                while (rs.next()) {
                    result.add(
                            constructProductFromResultSet(rs)
                    );
                }

                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        return null;
    }

    private Product constructProductFromResultSet(ResultSet rs) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getBigDecimal("price")
        );
    }
}
