package orders.console.menu.item;


import orders.console.menu.ExitClass;

public class MenuItemExit extends MenuItemBase implements MenuItem {

    private final ExitClass exit;

    public MenuItemExit(ExitClass exit) {
        super("wyjście z aplikacji");
        this.exit = exit;
    }

    @Override
    public void execte() {
        Message msg = new MessageConsole();
        msg.showMessage("informacja", "zakończono działanie aplikacji");
        exit.setExit(true);
    }
}
