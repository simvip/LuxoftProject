package ua.com.sliusar;

import ua.com.sliusar.veiw.MainMenu;

import java.io.IOException;

/**
 * Class ClientDAOInMemoryImpl
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class App {
    /**
     * Продумать админ пункты
     * Прописать логику на меню
     * POJO Product, Order
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        MainMenu menu = new MainMenu();
        menu.showMenu();
    }

}
