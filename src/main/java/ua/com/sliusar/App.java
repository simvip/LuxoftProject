package ua.com.sliusar;

import ua.com.sliusar.dao.ClientDAO;
import ua.com.sliusar.dao.OrderDAO;
import ua.com.sliusar.dao.ProductDAO;
import ua.com.sliusar.dao.impl.ClientDAOInMemoryImpl;
import ua.com.sliusar.dao.impl.OrderDAOInMemoryImpl;
import ua.com.sliusar.dao.impl.ProductDAOInMemoryImpl;
import ua.com.sliusar.services.ClientService;
import ua.com.sliusar.services.OrderService;
import ua.com.sliusar.services.ProductService;
import ua.com.sliusar.services.impl.ClientServiceImpl;
import ua.com.sliusar.services.impl.OrderServiceImpl;
import ua.com.sliusar.services.impl.ProductServiceImpl;
import ua.com.sliusar.validators.ValidationService;
import ua.com.sliusar.validators.impl.ValidationServiceImp;
import ua.com.sliusar.veiw.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class ClientDAOInMemoryImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class App {
    /**
     * 1. Реализовать ClientDAO singleton
     * 2. Написать ряд валидаторов: email,age,phone+3(xxx)xxx-xx-xx проверить код оператора 097 050
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        OrderDAO orderDAO = new OrderDAOInMemoryImpl();
        ClientDAO clientDAO = ClientDAOInMemoryImpl.getInstance();
        ProductDAO productDAO = new ProductDAOInMemoryImpl();

        ValidationService validationService = new ValidationServiceImp();
        ProductService productService = new ProductServiceImpl(productDAO,validationService);
        ClientService clientService = new ClientServiceImpl(clientDAO, validationService);
        OrderService orderService = new OrderServiceImpl(orderDAO, validationService);

        ProductMenu productMenu = new ProductMenu(br, productService);
        OrderMenu orderMenu = new OrderMenu(br, orderService);
        ClientMenu clientMenu = new ClientMenu(br, orderMenu, clientService);

        AdminMenu adminMenu = new AdminMenu(br, productMenu, orderMenu, clientMenu);
        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);

        menu.showMenu();
    }

}
