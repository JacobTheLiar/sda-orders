package orders.console.menu.item;

import static orders.console.retrieve.Input.clearConsole;

public class MessageConsole implements Message{


    public void showMessage(String caption, String message){
        clearConsole();
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("┌────────────────────────────────────────────────────┐\n");
        builder.append(String.format("│ %50s │\n", caption));
        builder.append("╞════════════════════════════════════════════════════╡\n");
        builder.append(String.format("│ %-50s │\n", message));
        builder.append("└────────────────────────────────────────────────────┘\n");
        System.out.print(builder);

    }

}
