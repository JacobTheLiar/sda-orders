package orders.console.menu;

import orders.console.menu.item.MenuItem;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static orders.console.retrieve.Input.getString;

public class MenuBase extends TreeMap<String, MenuItem> implements Menu {

    private final boolean autoExit;
    private String menuName;
    private final ExitClass exit;

    public MenuBase(String menuName, ExitClass exit) {
        this(menuName, exit, true);

    }

    public MenuBase(String menuName, ExitClass exit, boolean autoExit) {
        this.menuName = menuName;
        this.exit = exit;
        this.autoExit = autoExit;
    }
    public void execute(){

        StringBuilder builder = new StringBuilder();

        builder.append("┌────────────────────────────────────────────────────┐\n");
        builder.append(String.format("│ %50s │\n", menuName));
        builder.append("╞═══════╤════════════════════════════════════════════╡\n");
        builder.append("│ opcja │ opis                                       │\n");
        builder.append("├───────┼────────────────────────────────────────────┤\n");

        builder.append(getPrintMenuItems());

        builder.append("├───────┼────────────────────────────────────────────┘\n");

        System.out.print(builder);

        String option;
        boolean isOk;
        do {
            option = getOption().toUpperCase();
            isOk = executeItem(option);
            if (!isOk){
                printInvalidOption();
            } else {
                exit.setExit(autoExit || exit.canExit());
            }

        }while (!isOk);
    }


    private void printInvalidOption(){
        StringBuilder builder = new StringBuilder();
        builder.append("│       │ nieprawidłowa opcja, wybierz jeszcze raz.\n");
        builder.append("├───────┼─────────────────────────────────────────────\n");
        System.out.print(builder);
    }


    private String getPrintMenuItems(){
        return this.entrySet().stream()
                .map(itm -> String.format("│ %5s │ %-42s │\n", itm.getKey(), itm.getValue().getTitle()))
                .collect(Collectors.joining());
    }

/* // inne metody do wyświetlania menu
    private String getPrintMenuItems2(){
        StringBuilder builder = new StringBuilder();

        this.forEach((itemKey, menuItem) ->
            builder.append(String.format("│ %5s │ %-42s │\n", itemKey, menuItem.getTitle())));


        return builder.toString();
    }
    private String getPrintMenuItems(){
        StringBuilder builder = new StringBuilder();

        for (Entry<String, MenuItem> menuItem : this.entrySet()){
            String itemKey = menuItem.getKey();
            String title = menuItem.getValue().getTitle();
            builder.append(String.format("│ %5s │ %-42s │\n", itemKey, title));
        }

        return builder.toString();
    }
*/
    private String getOption(){

        System.out.print("│ wybór │");

        return getString();
    }

    private boolean executeItem(String choice){

        if (this.containsKey(choice)){
            printSelectedItem(this.get(choice).getTitle());
            this.get(choice).execte();
            return true;
        }
        return false;
    }

    private void printSelectedItem(String title){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("│       │ wybrano: %-33s │", title));
        builder.append("\n└───────┴────────────────────────────────────────────┘\n");
        System.out.print(builder);
    }

    public void addMenuItem(String choice, MenuItem item){

        if (choice == null){
            throw new IllegalArgumentException("brak opcji wyboru menu!");
        }

        if (item == null){
            throw new IllegalArgumentException("brak instancji menu");
        }

        if (this.containsKey(choice)){
            throw new ArrayStoreException("podany element menu "+choice+" już istnieje!");
        }

        this.put(choice, item);
    }

}
