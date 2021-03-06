package orders.console.menu.print;


import orders.console.menu.ExitClass;
import orders.console.menu.Menu;
import orders.console.menu.MenuBase;
import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemAbort;
import orders.console.menu.item.MenuItemBase;
import orders.core.Order;
import orders.core.StorageOrder;

import static orders.console.retrieve.Input.clearConsole;

public class MenuItemPrintOrder extends MenuItemBase implements MenuItem {

    private final StorageOrder storageOrder;

    private Menu menu;
    private ExitClass exit;

    public MenuItemPrintOrder(StorageOrder storageOrder) {
        super("drukuj zamówienie");
        this.storageOrder = storageOrder;
    }

    private void initializeMenu() {
        clearConsole();
        exit = new ExitClass();
        menu = new MenuBase("drukuj zamówinie", exit);

        int counter = 0;
        for (Order order : storageOrder.getOrderList()){
            MenuItem item = new PrintSelectedOrder(order.getOrderNumber(), storageOrder.getOrder(counter));
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
