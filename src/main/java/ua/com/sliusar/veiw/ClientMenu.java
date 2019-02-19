package ua.com.sliusar.veiw;

import ua.com.sliusar.domain.Client;
import ua.com.sliusar.services.ClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Class ClientMenu
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class ClientMenu {
    private final BufferedReader br;
    private final OrderMenu orderMenu;
    private ClientService clientService;

    public ClientMenu(BufferedReader br, OrderMenu orderMenu, ClientService clientService) {
        this.br = br;
        this.orderMenu = orderMenu;
        this.clientService = clientService;
    }

    public void showMainMenu() throws IOException {
        soutClientMenu();
        switch (br.readLine()) {
            case "1":
                createClient();
                this.showMainMenu();
                break;
            case "2":
                modifyClient();
                this.showMainMenu();
                break;
            case "3":
                deleteClientById();
                this.showMainMenu();
                break;
            case "4":
                listAllClients();
                this.showMainMenu();
                break;
            case "5":
                orderMenu.showMainMenu();
                break;
            case "R":
                break;
            case "E":
                MainMenu.isRunning = false;
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
        System.out.println("Input email");
        String email = br.readLine();
        System.out.println("Input phone");
        String phone = br.readLine();
        System.out.println("Input age");
        int age = readInteger();
        clientService.createClient(name, surname, phone, email, age);
    }

    private int readInteger() {
        try {
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.out.println("Input number please!");
            return readInteger();
        }
    }

    private void modifyClient() throws IOException {
        System.out.println("Input id client");
        String id = br.readLine();
        showUpdateMenu();
        switch (br.readLine()) {
            case "1":
                System.out.println("Input name");
                clientService.update(id, new HashMap<String, String>() {
                    {
                        put("name", br.readLine());
                    }
                });
                break;
            case "2":
                System.out.println("Input new phone number");
                clientService.update(id, new HashMap<String, String>() {
                    {
                        put("phone", br.readLine());
                    }
                });
                break;
            case "3":
                showMainMenu();
                break;
            default:
                showUpdateMenu();
                System.out.println("Wrong menu");
        }
    }

    private void listAllClients() {
        List<Client> all = clientService.findAll();
        if (all.size() == 0) System.out.println("List of clients is empty");
        for (Client client : all) {
            System.out.println(client);
        }
    }

    private void deleteClientById() throws IOException {
        System.out.println("Input id client");
        String id = br.readLine();
        clientService.delete(id);
    }

    private void soutClientMenu() {
        System.out.println("------------------");
        System.out.println("1. Add client");
        System.out.println("2. Modify client");
        System.out.println("3. Remove client");
        System.out.println("4. List all clients");
        System.out.println("------------------");
        System.out.println("5. -ORDER MENU-->");
        System.out.println("------------------");
        System.out.println("R. Return");
        System.out.println("E. Exit");
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
