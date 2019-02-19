package ua.com.sliusar.veiw;

import ua.com.sliusar.domain.Product;
import ua.com.sliusar.services.ProductService;

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
public class ProductMenu {
    private final BufferedReader br;
    private ProductService productService;

    public ProductMenu(BufferedReader br, ProductService productService) {
        this.br = br;
        this.productService = productService;
    }

    public void showMainMenu() throws IOException {
        soutMainMenu();
        switch (br.readLine()) {
            case "1":
                createProduct();
                this.showMainMenu();
                break;
            case "2":
                modifyProduct();
                this.showMainMenu();
                break;
            case "3":
                deleteProductById();
                this.showMainMenu();
                break;
            case "4":
                listAllProduct();
                this.showMainMenu();
                break;
            case "9":
                break;
            default:
                System.out.println("Wrong menu");
        }
    }

    private void createProduct() throws IOException {
        System.out.println("Input name");
        String name = br.readLine();
        System.out.println("Input price");
        String price = br.readLine();
        productService.create(name, price);
    }

    private void modifyProduct() throws IOException {
        System.out.println("Input products id");
        String id = br.readLine();
        showUpdateMenu();
        switch (br.readLine()) {
            case "1":
                System.out.println("Input new name");
                productService.update(id, new HashMap<String, String>() {
                    {
                        put("name", br.readLine());
                    }
                });
                break;
            case "2":
                System.out.println("Input new price");
                productService.update(id, new HashMap<String, String>() {
                    {
                        put("price", br.readLine());
                    }
                });
                break;
            case "3":
                soutMainMenu();
                break;
            default:
                showUpdateMenu();
                System.out.println("Wrong menu");
        }
    }

    private void listAllProduct() {
        List<Product> all = productService.findAll();
        if (all.size() == 0) System.out.println("List of products is empty");
        for (Product product : all) {
            System.out.println(product);
        }
    }

    private void deleteProductById() throws IOException {
        System.out.println("Input id product");
        String id = br.readLine();
        productService.delete(id);
    }

    private void soutMainMenu() {
        System.out.println("------------------");
        System.out.println("Choice operation:");
        System.out.println("1. Add Product");
        System.out.println("2. Modify Product");
        System.out.println("3. Remove Product");
        System.out.println("4. List all Product");
        System.out.println("9. Return main menu");
        System.out.println("0. Exit");
        System.out.println("------------------");
    }

    private void showUpdateMenu() {
        System.out.println("------------------");
        System.out.println("What do you want to update?");
        System.out.println("1. Name");
        System.out.println("2. Price");
        System.out.println("0. Return");
        System.out.println("------------------");
    }

}
