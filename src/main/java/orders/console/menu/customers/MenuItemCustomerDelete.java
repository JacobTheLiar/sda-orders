package orders.console.menu.customers;

import orders.console.menu.ExitClass;
import orders.console.menu.MenuBase;
import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemAbort;
import orders.console.menu.item.MenuItemBase;
import orders.core.Customer;
import orders.core.StorageCustomer;

import static orders.console.retrieve.Input.clearConsole;

public class MenuItemCustomerDelete extends MenuItemBase implements MenuItem {
    private final StorageCustomer customers;
    private ExitClass exit;
    private MenuBase menu;

    public MenuItemCustomerDelete(StorageCustomer customers) {
        super("usuń kontrahenta");
        this.customers = customers;
    }

    private void initializeMenu() {
        clearConsole();
        exit = new ExitClass();
        menu = new MenuBase("usuń kontrahenta", exit);

        int counter = 0;
        for (Customer customer : customers.getCustomerList()){
            MenuItem item = new DeleteSelectedCustomer(customer.getName(), customers, counter);
            String option = Integer.toString(counter+1);
            menu.addMenuItem(option, item);
            counter++;
        }

        menu.addMenuItem("X", new MenuItemAbort(exit));
    }

    @Override
    public void execte() {
        initializeMenu();

        do {
            System.out.println();
            menu.execute();

        } while (!exit.canExit());
    }
}
