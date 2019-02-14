package ua.com.sliusar.veiw;

import ua.com.sliusar.domain.Client;
import ua.com.sliusar.services.ClientService;
import ua.com.sliusar.services.impl.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

/**
 * Class AdminMenu
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class AdminMenu {
    private final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );
    private final ClientService clientService = new ClientServiceImpl();
    private MainMenu mainMenu;

    public AdminMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void show() throws IOException {

        showMainMenu();
        switch (br.readLine()) {
            case "1":
                createClient();
                this.show();
                break;
            case "2":
                modifyClient();
                this.show();
                break;
            case "3":
                deleteClientById();
                this.show();
                break;
            case "4":
                listAllClients();
                this.show();
                break;
            case "9":
                mainMenu.showMenu();
                break;
            case "0":
                mainMenu.isRunning = false;
                break;
            default:
                System.out.println("Wrong menu");
        }
    }

    private void createClient() throws IOException {
        System.out.println("Input name");
        String name = br.readLine();
        System.out.println("Input surname");
        String surname = br.readLine();
        System.out.println("Input phone");
        String phone = br.readLine();
        clientService.createClient(name, surname, phone);
    }

    private void modifyClient() throws IOException {
        System.out.println("Input id client");
        String id = br.readLine();
        Client client = clientService.findClient(id);
        if (client != null) {
            showUpdateMenu();
        } else {
            System.out.println("Client with such id " + id + " doesn`t find");
            showUpdateMenu();
        }
        switch (br.readLine()) {
            case "1":
                System.out.println("Input name");
                clientService.updateClient(id, new HashMap<String, String>() {
                    {
                        put("name", br.readLine());
                    }
                });
                break;
            case "2":
                System.out.println("Input new phone number");
                clientService.updateClient(id, new HashMap<String, String>() {
                    {
                        put("phone", br.readLine());
                    }
                });
                break;
            case "3": // Return
                showMainMenu();
                break;
            default:
                showUpdateMenu();
                System.out.println("Wrong menu");
        }
    }

    private void listAllClients() {
        List<Client> all = clientService.findAll();
        for (Client client : all) {
            System.out.println(client);
        }
    }

    private void deleteClientById() throws IOException {
        System.out.println("Input id client");
        String id = br.readLine();
        clientService.deleteClient(id);
    }

    private void showMainMenu() {
        System.out.println("------------------");
        System.out.println("Choice operation:");
        System.out.println("1. Add Client");
        System.out.println("2. Modify client");
        System.out.println("3. Remove client");
        System.out.println("4. List all clients");
        System.out.println("9. Return");
        System.out.println("0. Exit");
        System.out.println("------------------");
    }

    private void showUpdateMenu() {
        System.out.println("------------------");
        System.out.println("What do you want to update?");
        System.out.println("1. Name");
        System.out.println("2. Telephone");
        System.out.println("0. Return");
        System.out.println("------------------");
    }
}
