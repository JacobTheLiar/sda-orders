package orders.console.menu.add;


import orders.console.menu.ExitClass;
import orders.console.menu.Menu;
import orders.console.menu.MenuBase;
import orders.console.menu.customers.MenuItemCustomerAdd;
import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemAbort;
import orders.console.menu.item.MenuItemBase;
import orders.core.Customer;
import orders.core.StorageCustomer;
import orders.core.StorageOrder;

import static orders.console.retrieve.Input.clearConsole;


public class MenuItemNewOrder extends MenuItemBase implements MenuItem {

    private final StorageOrder storageOrder;
    private final StorageCustomer storageCustomer;
    private Customer issueBy;
    private ExitClass exit;
    private Menu menu;

    public MenuItemNewOrder(StorageOrder storageOrder, Customer issueBy, StorageCustomer storageCustomer) {
        super("dodaj nowe zamówienie");
        this.storageOrder = storageOrder;
        this.storageCustomer = storageCustomer;
        this.issueBy = issueBy;
    }

    @Override
    public void execte() {
        initializeMenu();

        do{
            menu.execute();
        }while(!exit.canExit());

    }

    private void initializeMenu() {
        clearConsole();
        exit = new ExitClass();
        menu = new MenuBase("wybierz kontrahenta do rachunku", exit);

        int counter = 0;
        for (Customer customer : storageCustomer.getCustomerList()){
            MenuItem item = new MenuItemNewCustomerOrder(customer.getName(), customer, issueBy, storageOrder);
            String option = Integer.toString(counter+1);
            menu.addMenuItem(option, item);
            counter++;
        }

        menu.addMenuItem("0", new MenuItemCustomerAdd(storageCustomer));
        menu.addMenuItem("X", new MenuItemAbort(exit));
    }



}
