package orders.console.menu.customers;

import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemBase;
import orders.console.menu.item.Message;
import orders.console.menu.item.MessageConsole;
import orders.core.StorageCustomer;

public class DeleteSelectedCustomer extends MenuItemBase implements MenuItem {
    private final StorageCustomer customers;
    private final int id;

    public DeleteSelectedCustomer(String name, StorageCustomer customers, int id) {
        super(name);
        this.customers = customers;
        this.id = id;
    }

    @Override
    public void execte() {
        String name = customers.getCustomer(id).getName();
        customers.deleteCustomer(id);

        Message msg = new MessageConsole();
        msg.showMessage("informacja", "usunięto kontrahenta \""+name+"\"");

    }
}
