package orders.console.menu.customers;


import orders.console.menu.ExitClass;
import orders.console.menu.Menu;
import orders.console.menu.MenuBase;
import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemAbort;
import orders.console.menu.item.MenuItemBase;
import orders.core.StorageCustomer;

import static orders.console.retrieve.Input.clearConsole;

public class MenuItemCustomers extends MenuItemBase implements MenuItem {

    private final StorageCustomer storageCustomer;
    private Menu menu;
    private ExitClass exitMenu;

    public MenuItemCustomers(StorageCustomer storageCustomer) {
        super("zarządzaj kontrahentami");
        this.storageCustomer = storageCustomer;
        this.exitMenu = new ExitClass();
        initializeMenu();
    }

    private void initializeMenu() {
        menu = new MenuBase("zarządzaj kontrahentami", exitMenu, false);

        menu.addMenuItem("1", new MenuItemCustomerAdd(storageCustomer));
        menu.addMenuItem("2", new MenuItemCustomerList(storageCustomer));
        menu.addMenuItem("3", new MenuItemCustomerDelete(storageCustomer));
        menu.addMenuItem("X", new MenuItemAbort(exitMenu));
    }

    @Override
    public void execte() {
        clearConsole();
        do{
            menu.execute();
        } while(!exitMenu.canExit());


    }
}
