package ua.com.sliusar.veiw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class MainMenu
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class MainMenu {
    public boolean isRunning = false;
    private final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );
    private final AdminMenu adminMenu = new AdminMenu(this);
    private final ClientMenu clientMenu = new ClientMenu(this);

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
                    adminMenu.show();
                    break;
                case "2":
                    clientMenu.show();
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
