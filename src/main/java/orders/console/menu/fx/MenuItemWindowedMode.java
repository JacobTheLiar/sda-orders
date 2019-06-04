package orders.console.menu.fx;

import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemBase;
import orders.core.MyApplication;
import orders.core.StorageCustomer;
import orders.core.StorageOrder;
import orders.fx.OrdersFXApp;

public class MenuItemWindowedMode extends MenuItemBase implements MenuItem {


    private final StorageCustomer storageCustomer;
    private final StorageOrder storageOrder;

    public MenuItemWindowedMode(StorageOrder storageOrder, StorageCustomer storageCustomer) {
        super("wersja okienkowa JavaFX");

        this.storageCustomer = storageCustomer;
        this.storageOrder = storageOrder;

    }

    @Override
    public void execte() {

        MyApplication windowed = new OrdersFXApp(storageOrder, storageCustomer);
        windowed.run();

    }
}
