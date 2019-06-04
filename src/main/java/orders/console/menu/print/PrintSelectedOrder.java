package orders.console.menu.print;

import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemBase;
import orders.core.Order;

import static orders.console.retrieve.Input.clearConsole;
import static orders.console.retrieve.Input.waitForEnter;

public class PrintSelectedOrder  extends MenuItemBase implements MenuItem {
    private final Order order;

    public PrintSelectedOrder(String title, Order order) {
        super(title);
        this.order = order;
    }

    @Override
    public void execte() {
        clearConsole();
        order.printOrder();
        waitForEnter();
        clearConsole();
    }
}
