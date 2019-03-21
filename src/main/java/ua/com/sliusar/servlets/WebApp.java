package ua.com.sliusar.servlets;

import ua.com.sliusar.dao.ClientDao;
import ua.com.sliusar.dao.OrderDao;
import ua.com.sliusar.dao.ProductDao;
import ua.com.sliusar.dao.impl.ClientDaoDBImpl;
import ua.com.sliusar.dao.impl.OrderDaoDBImpl;
import ua.com.sliusar.dao.impl.ProductDaoDBImpl;
import ua.com.sliusar.services.ClientService;
import ua.com.sliusar.services.OrderService;
import ua.com.sliusar.services.ProductService;
import ua.com.sliusar.services.impl.ClientServiceImpl;
import ua.com.sliusar.services.impl.OrderServiceImpl;
import ua.com.sliusar.services.impl.ProductServiceImpl;
import ua.com.sliusar.validators.ValidationService;
import ua.com.sliusar.validators.impl.ValidationServiceImp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Class WebApp
 *
 * @author create by ivanslusar
 * 3/15/19
 * @project MyLuxoftProject
 */
@WebListener
public class WebApp implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String DB_URL = "jdbc:h2:tcp://localhost/~/JavaProjects/MyLuxoftProject/src/main/resources/DB/WorkBase";
        String USER = "sa";
        String PASSWORD = "";

        ClientDao clientDAO = new ClientDaoDBImpl(DB_URL, USER, PASSWORD);
        ProductDao productDAO = new ProductDaoDBImpl(DB_URL, USER, PASSWORD);
        OrderDao orderDAO = new OrderDaoDBImpl(DB_URL, USER, PASSWORD, productDAO, clientDAO);

        ValidationService validationService = new ValidationServiceImp();
        ProductService productService = new ProductServiceImpl(productDAO, validationService);
        ClientService clientService = new ClientServiceImpl(clientDAO, validationService);
        OrderService orderService = new OrderServiceImpl(orderDAO, productService, clientService, validationService);

        ServletContext sc = sce.getServletContext();
        sc.addServlet("ClientServlet", new ClientServlet(clientService)).addMapping("/clients");
        sc.addServlet("ProductServlet", new ProductServlet(productService)).addMapping("/products");
        sc.addServlet("OrderServlet", new OrderServlet(orderService)).addMapping("/orders");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
