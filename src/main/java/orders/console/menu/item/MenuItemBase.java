package orders.console.menu.item;



public abstract class MenuItemBase {

    private String title;

    public MenuItemBase(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}