package ua.com.sliusar;

import ua.com.sliusar.dao.ClientDao;
import ua.com.sliusar.dao.OrderDao;
import ua.com.sliusar.dao.ProductDao;
import ua.com.sliusar.dao.impl.ClientDaoInMemoryImpl;
import ua.com.sliusar.dao.impl.OrderDaoInMemoryImpl;
import ua.com.sliusar.dao.impl.ProductDaoInMemoryImpl;
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
 * Class ClientDaoInMemoryImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class App {
    /**
     * 1. Реализовать ClientDao singleton
     * 2. Написать ряд валидаторов: email,age,phone+3(xxx)xxx-xx-xx проверить код оператора 097 050
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        OrderDao orderDAO = new OrderDaoInMemoryImpl();
        ClientDao clientDAO = ClientDaoInMemoryImpl.getInstance();
        ProductDao productDAO = new ProductDaoInMemoryImpl();

        ValidationService validationService = new ValidationServiceImp();
        ProductService productService = new ProductServiceImpl(productDAO,validationService);
        ClientService clientService = new ClientServiceImpl(clientDAO, validationService);
        OrderService orderService = new OrderServiceImpl(orderDAO,productService);

        ProductMenu productMenu = new ProductMenu(br, productService);
        OrderMenu orderMenu = new OrderMenu(br, orderService);
        ClientMenu clientMenu = new ClientMenu(br, orderMenu, clientService);

        AdminMenu adminMenu = new AdminMenu(br, productMenu, orderMenu, clientMenu);
        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);

        menu.showMenu();
    }

}
