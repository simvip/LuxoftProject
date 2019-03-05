package ua.com.sliusar.dao.impl;

import ua.com.sliusar.dao.OrderDao;
import ua.com.sliusar.domain.Order;
import ua.com.sliusar.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class OrderDaoDBImpl
 *
 * @author create by ivanslusar
 * 3/5/19
 * @project MyLuxoftProject
 */
public class OrderDaoDBImpl implements OrderDao {

    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/JavaProjects/MyLuxoftProject/src/main/resources/DB/WorkBase";
    public static final String USER = "sa";
    public static final String PASSWORD = "";

    private Connection connection;

    public OrderDaoDBImpl() {
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
    public List<Order> findAllOrderOfClient(Long clientID) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM MAINORDER WHERE MAINORDER.CLIENTID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, clientID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(constructOrderFromStatment(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public boolean createOrUpdate(Order order) {
        String mainOrder;
        boolean needToCreate = order.getId() == null;
        if (needToCreate) {
            mainOrder = "INSERT INTO MAINORDER (CLIENTID,TOTALPRICE) VALUES (?,?)";
        } else {
            mainOrder = "UPDATE MAINORDER SET CLIENTID = ?,TOTALPRICE = ? WHERE MAINORDER.ID = ?";
        }
        String productOrder = "INSERT INTO ORDER_PRODUCTS (IDORDER,IDPRODUCT) VALUES (?,?)";
        try (
                PreparedStatement stmtMain = connection.prepareStatement(mainOrder);
                PreparedStatement stmtOrder_Product = connection.prepareStatement(productOrder)
        ) {
            stmtMain.setLong(1, order.getClient().getId());
            stmtMain.setBigDecimal(2, order.getTotalPrice());
            if (!needToCreate) {
                stmtMain.setLong(3, order.getId());
            }
            stmtMain.executeUpdate();
            for (Product item : order.getProductList()) {
                stmtOrder_Product.setLong(1, order.getId());
                stmtOrder_Product.setLong(2, item.getId());
                stmtOrder_Product.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        String mainOrder = "DELETE FROM MAINORDER WHERE MAINORDER.ID = ?";
        String productOrder = "DELETE FROM ORDER_PRODUCTS WHERE ORDER_PRODUCTS.IDPRODUCT = ?";
        try (
                PreparedStatement mainStmt = connection.prepareStatement(mainOrder);
                PreparedStatement orderStmt = connection.prepareStatement(productOrder);
        ) {
            mainStmt.setLong(1, id);
            mainStmt.executeUpdate();
            orderStmt.setLong(1, id);
            orderStmt.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order findById(Long id) {
        String query = "SELECT * FROM MAINORDER WHERE MAINORDER.ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    return constructOrderFromStatment(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM MAINORDER";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(constructOrderFromStatment(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Order constructOrderFromStatment(ResultSet rs) {

        try {
            Order order = new Order(
                    rs.getLong("Id"),
                    rs.getBigDecimal("totalprice")
            );
            order.setClient( new ClientDaoDBImpl()
                    .findById(
                            rs.getLong("clientId"))

            );
            order.setProductList(getProductsByHisOwner(
                    rs.getLong("idOrder")
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return null;
    }

    private List<Product> getProductsByHisOwner(Long idOrder) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM ORDER_PRODUCTS WHERE ORDER_PRODUCTS.IDORDER = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, idOrder);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(
                            new ProductDaoDBImpl()
                                    .findById(
                                            rs.getLong("idProduct")
                                    )
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
