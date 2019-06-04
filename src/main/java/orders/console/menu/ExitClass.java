package orders.console.menu;

public class ExitClass {

    public ExitClass() {
        exit = false;
    }

    public boolean canExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    private boolean exit;

}
