package orders.console.menu.list;

import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemBase;
import orders.console.menu.item.Message;
import orders.console.menu.item.MessageConsole;
import orders.core.Order;
import orders.core.StorageOrder;

import static orders.console.retrieve.Input.clearConsole;
import static orders.console.retrieve.Input.waitForEnter;

public class MenuItemOrderList extends MenuItemBase implements MenuItem {

    private final StorageOrder storageOrder;

    public MenuItemOrderList(StorageOrder storageOrder) {
        super("wyświetl listę zamówień");
        this.storageOrder = storageOrder;
    }

    @Override
    public void execte() {
        clearConsole();
        if (storageOrder.getOrderCount()==0){
            Message msg = new MessageConsole();
            msg.showMessage("lista zapisanych zamówień", "brak zapisanych zamówień");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("┌────────────────────────────────────────────────────┐\n");
        builder.append("│                          lista zapisanych zamówień │\n");
        builder.append("╞══════╤═════════════════════════════════════════════╡\n");
        builder.append("│  id  │ numer zamówienia                            │\n");
        builder.append("├──────┼─────────────────────────────────────────────┤\n");
        builder.append(printOrderList());
        builder.append("├──────┴─────────────────────────────────────────────┤\n");
        builder.append("│ naciśnij ENTER by kontynuować                      │\n");
        builder.append("└────────────────────────────────────────────────────┘\n");
        System.out.print(builder);
        waitForEnter();
        clearConsole();
    }

    private String printOrderList() {
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        for (Order order : storageOrder.getOrderList()){
            builder.append(String.format("│%5d │ %-43s │\n", counter, order.getOrderNumber()));
            counter++;
        }
        return builder.toString();
    }
}
