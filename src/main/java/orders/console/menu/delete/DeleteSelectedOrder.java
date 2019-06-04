package orders.console.menu.delete;

import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemBase;
import orders.console.menu.item.Message;
import orders.console.menu.item.MessageConsole;
import orders.core.StorageOrder;

public class DeleteSelectedOrder extends MenuItemBase implements MenuItem {
    private final StorageOrder storageOrder;
    private final int index;

    public DeleteSelectedOrder(String title, StorageOrder storageOrder, int index) {
        super(title);
        this.storageOrder = storageOrder;
        this.index = index;
    }

    @Override
    public void execte() {
        String orderNumber = storageOrder.getOrder(index).getOrderNumber();
        storageOrder.deleteOrder(index);

        Message msg = new MessageConsole();
        msg.showMessage("informacja", "usunięto zamówienie nr "+orderNumber);

    }


}
