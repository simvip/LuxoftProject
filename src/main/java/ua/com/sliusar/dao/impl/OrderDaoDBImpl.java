package ua.com.sliusar.dao.impl;

import ua.com.sliusar.dao.ClientDao;
import ua.com.sliusar.dao.OrderDao;
import ua.com.sliusar.dao.ProductDao;
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
    private String db_url;
    private String user;
    private String password;
    private ProductDao productDaoDB;
    private ClientDao clientDaoDB;

    public OrderDaoDBImpl(String db_url, String user, String password, ProductDao productDaoDB, ClientDao clientDaoDB) {
        this.db_url = db_url;
        this.user = user;
        this.password = password;
        this.productDaoDB = productDaoDB;
        this.clientDaoDB = clientDaoDB;
    }

    @Override
    public List<Order> findAllOrderOfClient(Long clientID) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            List<Order> orders = new ArrayList<>();
            String query = "SELECT * FROM MAIN_ORDER WHERE MAIN_ORDER.CLIENT_ID = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setLong(1, clientID);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        orders.add(constructOrderFromResultSet(rs));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return orders;
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createOrUpdate(Order order) {
        if (order.getId() == null) {
            return createOrder(order);
        } else {
            return updateOrder(order);
        }
    }

    private boolean createOrder(Order order) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            String mainOrder = "INSERT INTO MAIN_ORDER (CLIENT_ID,TOTAL_PRICE) VALUES (?,?)";
            try (PreparedStatement stmtMain = connection.prepareStatement(mainOrder)) {
                stmtMain.setLong(1, order.getClient().getId());
                stmtMain.setBigDecimal(2, order.getTotalPrice());
                stmtMain.executeUpdate();
                // if we create new order, we need to update id in current instance.
                ResultSet rs = stmtMain.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getLong(1));
                }
                createOrUpdateOrderProduct(order);
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

    private boolean updateOrder(Order order) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            String mainOrder = "UPDATE MAIN_ORDER SET CLIENT_ID = ?,TOTAL_PRICE = ? WHERE MAIN_ORDER.ID = ?";
            try (PreparedStatement stmtMain = connection.prepareStatement(mainOrder)) {
                stmtMain.setLong(1, order.getClient().getId());
                stmtMain.setBigDecimal(2, order.getTotalPrice());
                stmtMain.setLong(3, order.getId());
                stmtMain.executeUpdate();
                createOrUpdateOrderProduct(order);
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

    private void createOrUpdateOrderProduct(Order order) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            String productOrder = "INSERT INTO ORDER_PRODUCTS (ID_PRODUCT,ID_ORDER) VALUES (?,?)";
            String productOrderClean = "DELETE FROM ORDER_PRODUCTS WHERE ORDER_PRODUCTS.ID_ORDER = ?";
            try (
                    PreparedStatement stmt = connection.prepareStatement(productOrder);
                    PreparedStatement stmtClean = connection.prepareStatement(productOrderClean);
            ) {
                // clean table by Order id
                stmtClean.setLong(1, order.getId());
                stmtClean.executeUpdate();
                // add new records
                for (Product item : order.getProductList()) {
                    stmt.setLong(1, item.getId());
                    stmt.setLong(2, order.getId());
                    stmt.executeUpdate();
                }
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            String mainOrder = "DELETE FROM MAIN_ORDER WHERE MAIN_ORDER.ID = ?";
            try (
                    PreparedStatement mainStmt = connection.prepareStatement(mainOrder);
            ) {
                mainStmt.setLong(1, id);
                mainStmt.executeUpdate();
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
    public Order findById(Long id) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            String query = "SELECT * FROM MAIN_ORDER WHERE MAIN_ORDER.ID = ?";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        return constructOrderFromResultSet(rs);
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
    public List<Order> findAll() {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            List<Order> orders = new ArrayList<>();
            String query = "SELECT * FROM MAIN_ORDER";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        orders.add(constructOrderFromResultSet(rs));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return orders;
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        return null;
    }

    private Order constructOrderFromResultSet(ResultSet rs) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        try {
            Order order = new Order(
                    rs.getLong("Id"),
                    rs.getBigDecimal("total_price")
            );
            order.setClient(clientDaoDB
                    .findById(
                            rs.getLong("client_Id"))

            );
            order.setProductList(getProductsByHisOwner(
                    rs.getLong("id")
            ));
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Product> getProductsByHisOwner(Long idOrder) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            List<Product> products = new ArrayList<>();
            String query = "SELECT * FROM ORDER_PRODUCTS WHERE ORDER_PRODUCTS.ID_ORDER = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setLong(1, idOrder);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        products.add(
                                this.productDaoDB
                                        .findById(
                                                rs.getLong("id_Product")
                                        )
                        );
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return products;
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
        return null;
    }
}
