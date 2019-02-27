package ua.com.sliusar.veiw;

import ua.com.sliusar.domain.Order;
import ua.com.sliusar.services.OrderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Class AdminMenu
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class OrderMenu {
    private final BufferedReader br;
    private OrderService orderService;

    public OrderMenu(BufferedReader br, OrderService orderService) {
        this.br = br;
        this.orderService = orderService;
    }

    private void createOrder() throws IOException {
        System.out.println("Input Client id");
        String name = br.readLine();
        System.out.println("Input price");
        String price = br.readLine();
        orderService.create(name, price);
    }

    private void modifyOrder() throws IOException {
        System.out.println("Input order id");
        String id = br.readLine();
        updateMenu();
        switch (br.readLine()) {
            case "1": //change client ID
                System.out.println("Input new client ID");
                orderService.update(id, new HashMap<String, String>() {
                    {
                        put("clientId", br.readLine());
                    }
                });
                break;
            case "2":// Add Product
                System.out.println("Input ID product");
                orderService.update(id, new HashMap<String, String>() {
                    {
                        put("idProductAddToOrder", br.readLine());
                    }
                });
                break;
            case "3":// Delete Product
                System.out.println("Input ID Product");
                orderService.update(id, new HashMap<String, String>() {
                    {
                        put("idProductDeleteFromOrder", br.readLine());
                    }
                });
                break;
            case "9":
                mainMenu();
                break;
            case "0":
                break;
            default:
                updateMenu();
                System.out.println("Wrong menu");
        }
    }

    private void updateMenu() {
        System.out.println("------------------");
        System.out.println("What do you want to update?");
        System.out.println("1. Client ID");
        System.out.println("2. Add product");
        System.out.println("3. Delete product");
        System.out.println("0. Return");
        System.out.println("------------------");
    }

    private void listAllOrder() {
        List<Order> all = orderService.findAll();
        if (all.size() == 0) System.out.println("List of orders is empty");
        for (Order order : all) {
            System.out.println(order);
        }
    }

    private void deleteOrderById() throws IOException {
        System.out.println("Input orders id");
        String id = br.readLine();
        orderService.delete(id);
    }

    private void mainMenu() {
        System.out.println("------------------");
        System.out.println("Choice operation:");
        System.out.println("1. Add order");
        System.out.println("2. Modify order");
        System.out.println("3. Remove order");
        System.out.println("4. List all orders");
        System.out.println("9. Return main menu");
        System.out.println("0. Exit");
        System.out.println("------------------");
    }

    public void viewOnlyMenu() {
        System.out.println("1. Modify order");
        System.out.println("2. Remove order");
        System.out.println("3. List all orders");
        System.out.println("9. Return main menu");
        System.out.println("0. Exit");
    }

    public void showMainMenu() throws IOException {
        mainMenu();
        menuHandler();
    }

    public void showViewOnlyMenu() throws IOException {
        viewOnlyMenu();
        menuHandler();
    }

    private void menuHandler() throws IOException {
        switch (br.readLine()) {
            case "1":
                createOrder();
                this.showMainMenu();
                break;
            case "2":
                modifyOrder();
                this.showMainMenu();
                break;
            case "3":
                deleteOrderById();
                this.showMainMenu();
                break;
            case "4":
                listAllOrder();
                this.showMainMenu();
                break;
            case "9":
                //        adminMenu.showMainMenu();
                break;
            default:
                System.out.println("Wrong menu");
        }
    }

}
