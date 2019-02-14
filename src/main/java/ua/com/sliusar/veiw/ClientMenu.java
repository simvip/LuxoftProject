package ua.com.sliusar.veiw;

import ua.com.sliusar.services.ClientService;
import ua.com.sliusar.services.impl.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Class ClientMenu
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class ClientMenu {
    private final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );
    private final ClientService clientService = new ClientServiceImpl();
    private MainMenu mainMenu;

    public ClientMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void show() {
        System.out.println("Client menu");
    }
}
