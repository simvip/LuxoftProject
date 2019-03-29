package ua.com.sliusar.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Class WebApp
 *
 * @author create by ivanslusar
 * 3/15/19
 * @project MyLuxoftProject
 */
//@WebListener
public class WebApp implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        String DB_URL = "jdbc:h2:tcp://localhost/~/JavaProjects/MyLuxoftProject/src/main/resources/DB/WorkBase";
//        String USER = "sa";
//        String PASSWORD = "";
//
//        ClientDao clientDAO = new ClientDaoDBImpl(DB_URL, USER, PASSWORD);
//        ProductDao productDAO = new ProductDaoDBImpl(DB_URL, USER, PASSWORD);
//        OrderDao orderDAO = new OrderDaoDBImpl(DB_URL, USER, PASSWORD, productDAO, clientDAO);
//
//        ValidationService validationService = new ValidationServiceImp();
//        ProductService productService = new ProductServiceImpl(productDAO, validationService);
//        ClientService clientService = new ClientServiceImpl(clientDAO, validationService);
//        OrderService orderService = new OrderServiceImpl(orderDAO, productService, clientService, validationService);
//
//        ServletContext sc = sce.getServletContext();
//        sc.addServlet("ClientServlet", new ClientServlet(clientService)).addMapping("/clients");
//        sc.addServlet("ProductServlet", new ProductServlet(productService)).addMapping("/products");
//        sc.addServlet("OrderServlet", new OrderServlet(orderService)).addMapping("/orders");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
