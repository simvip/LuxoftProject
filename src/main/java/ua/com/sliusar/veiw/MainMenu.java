package ua.com.sliusar.veiw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class MainMenu
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class MainMenu {

    public static boolean isRunning = false;
    private BufferedReader br;
    private AdminMenu adminMenu;
    private ClientMenu clientMenu;

    public MainMenu(BufferedReader br, AdminMenu adminMenu, ClientMenu clientMenu) {
        this.br = br;
        this.adminMenu = adminMenu;
        this.clientMenu = clientMenu;
    }

    public void showMenu() throws IOException {
        isRunning = true;
        while (isRunning) {
            System.out.println("------------------");
            System.out.println("Choice operation:");
            System.out.println("1. Admin");
            System.out.println("2. Client");
            System.out.println("0. Exit");
            System.out.println("------------------");

            switch (br.readLine()) {
                case "1":
                    adminMenu.showMainMenu();
                    break;
                case "2":
                    clientMenu.showMainMenu();
                    break;
                case "0":
                    System.out.println("Exit");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong menu");

            }
        }
    }
}
