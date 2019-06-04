package orders.console.menu.customers;

import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemBase;
import orders.console.menu.item.Message;
import orders.console.menu.item.MessageConsole;
import orders.core.Customer;
import orders.core.StorageCustomer;

import static orders.console.retrieve.Input.clearConsole;
import static orders.console.retrieve.Input.waitForEnter;

public class MenuItemCustomerList extends MenuItemBase implements MenuItem {
    private final StorageCustomer customers;

    public MenuItemCustomerList(StorageCustomer customers) {
        super("wyświetl listę kontrahentów");

        this.customers = customers;
    }

    @Override
    public void execte() {

        if (customers.getCustomerCount()==0){
            Message msg = new MessageConsole();
            msg.showMessage("lista kontrahentów", "lista kontrahentów jest pusta");
            return;
        }
        clearConsole();
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("┌────────────────────────────────────────────────────┐\n");
        builder.append("│                      lista zapisanych kontrahentów │\n");
        builder.append("╞══════╤═════════════════════════════════════════════╡\n");
        builder.append("│  id  │ nazwa kontrahenta                           │\n");
        builder.append("├──────┼─────────────────────────────────────────────┤\n");
        builder.append(printCustomerList());
        builder.append("├──────┴─────────────────────────────────────────────┤\n");
        builder.append("│ naciśnij ENTER by kontynuować                      │\n");
        builder.append("└────────────────────────────────────────────────────┘\n");
        System.out.print(builder);
        waitForEnter();
        clearConsole();
    }

    private String printCustomerList() {
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        for (Customer order : customers.getCustomerList()){
            builder.append(String.format("│%5d │ %-43s │\n", counter, order.getName()));
            counter++;
        }
        return builder.toString();
    }
}
