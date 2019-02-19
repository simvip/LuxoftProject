package ua.com.sliusar.veiw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class AdminMenu
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class AdminMenu {
    private final BufferedReader br;
    private final ProductMenu productMenu;
    private final OrderMenu orderMenu;
    private final ClientMenu clientMenu;

    public AdminMenu(BufferedReader br, ProductMenu productMenu, OrderMenu orderMenu, ClientMenu clientMenu) {
        this.br = br;
        this.productMenu = productMenu;
        this.orderMenu = orderMenu;
        this.clientMenu = clientMenu;
    }

    public void showMainMenu() throws IOException {
        soutMainMenu();
        switch (br.readLine()) {
            case "1":
                clientMenu.showMainMenu();
                this.showMainMenu();
                break;
            case "2":
                productMenu.showMainMenu();
                break;
            case "3":
                orderMenu.showViewOnlyMenu();
                break;
            case "R":
                showMainMenu();
                break;
            case "E":
                MainMenu.isRunning = false;
                break;
            default:
                System.out.println("Wrong menu");
        }
    }
    private void soutMainMenu() {
        System.out.println("Choice menu:");
        System.out.println("------------------");
        System.out.println("1. MENU CLIENT--->");
        System.out.println("2.-MENU PRODUCT--> ");
        System.out.println("3.-MENU ORDER----> ");
        System.out.println("------------------");
        System.out.println("R. Return");
        System.out.println("E. Exit");
        System.out.println("------------------");
    }
}
