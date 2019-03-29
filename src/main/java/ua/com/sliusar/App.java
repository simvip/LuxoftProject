package ua.com.sliusar;

import java.io.IOException;

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
//        String DB_URL = "jdbc:h2:tcp://localhost/~/JavaProjects/MyLuxoftProject/src/main/resources/DB/WorkBase";
//        String USER = "sa";
//        String PASSWORD = "";
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
//        ProductMenu productMenu = new ProductMenu(br, productService);
//        OrderMenu orderMenu = new OrderMenu(br, orderService);
//        ClientMenu clientMenu = new ClientMenu(br, orderMenu, clientService);
//
//        AdminMenu adminMenu = new AdminMenu(br, productMenu, orderMenu, clientMenu);
//        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);
//
//        menu.showMenu();
    }

}
