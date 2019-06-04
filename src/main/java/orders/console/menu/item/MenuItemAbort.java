package orders.console.menu.item;


import orders.console.menu.ExitClass;

import static orders.console.retrieve.Input.clearConsole;

public class MenuItemAbort extends MenuItemBase implements MenuItem {

    private final ExitClass exit;

    public MenuItemAbort(ExitClass exit) {
        super("wróć do poprzedniego menu");
        this.exit = exit;
    }

    @Override
    public void execte() {
        clearConsole();
        exit.setExit(true);
    }
}
