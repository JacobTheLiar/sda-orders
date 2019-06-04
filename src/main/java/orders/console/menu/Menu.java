package orders.console.menu;

import orders.console.menu.item.MenuItem;

public interface Menu {

    void execute();
    void addMenuItem(String choice, MenuItem item);

}
