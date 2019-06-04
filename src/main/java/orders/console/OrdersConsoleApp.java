package orders.console;


import orders.console.menu.ExitClass;
import orders.console.menu.Menu;
import orders.console.menu.MenuBase;
import orders.console.menu.add.MenuItemNewOrder;
import orders.console.menu.customers.MenuItemCustomers;
import orders.console.menu.delete.MenuItemDeleteOrder;
import orders.console.menu.example.MenuItemAddExample;
import orders.console.menu.fx.MenuItemWindowedMode;
import orders.console.menu.item.MenuItemExit;
import orders.console.menu.list.MenuItemOrderList;
import orders.console.menu.print.MenuItemPrintOrder;
import orders.core.*;

import static orders.console.retrieve.Input.clearConsole;

public class OrdersConsoleApp implements MyApplication {

    private Menu menu;
    private ExitClass exitApplication;
    private Customer isseBy;
    private StorageOrder storageOrder;
    private StorageCustomer storageCustomer;

    public OrdersConsoleApp(){
        storageOrder = new StorageOrderList();
        storageCustomer = new StorageCustomerList();
        initializeIssue();
        initializeMenu();
    }

    @Override
    public void run() {
        clearConsole();
        do {
            menu.execute();
        } while (!exitApplication.canExit());
    }

    private void initializeMenu() {
        exitApplication = new ExitClass();
        menu = new MenuBase("główne menu", exitApplication, false);
        menu.addMenuItem("1", new MenuItemNewOrder(storageOrder, isseBy, storageCustomer));
        menu.addMenuItem("2", new MenuItemOrderList(storageOrder));
        menu.addMenuItem("3", new MenuItemPrintOrder(storageOrder));
        menu.addMenuItem("4", new MenuItemDeleteOrder(storageOrder));
        menu.addMenuItem("5", new MenuItemAddExample(storageOrder, isseBy, storageCustomer));
        menu.addMenuItem("6", new MenuItemCustomers(storageCustomer));
        menu.addMenuItem("W", new MenuItemWindowedMode(storageOrder, storageCustomer));
        menu.addMenuItem("Q", new MenuItemExit(exitApplication));
    }

    private void initializeIssue() {
        isseBy = new CustomerImp("Koleje Wielkopolskie Sp. z o. o.", "ul. Składowa 5",
                "61-897", "Poznań", "PL7781469734", "PL");
    }
}