package orders.console.menu.print;

import orders.core.Customer;
import orders.core.OrderBase;
import orders.core.OrderItemData;

import java.util.Scanner;

public class OrderImp extends OrderBase {

    public OrderImp(Customer issuedBy, Customer customer, String orderNumber) {
        super(issuedBy, customer, orderNumber);
    }

    @Override
    public void printOrder() {
        System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.print(getPrintMultiLines("zamowienie nr "+ orderNumber +" z dnia "+ getIssueDate()));
        System.out.println("╞═════════════════════════════════════════════════════════════════════════════════════╡");
        System.out.println("│ Wystawca                                                                            │");
        System.out.print(getPrintMultiLines(issuedBy.getBillDescription()));

        System.out.println("├─────────────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│ Odbiorca                                                                            │");
        System.out.print(getPrintMultiLines(customer.getBillDescription()));

        System.out.println("╞═════════════════════════════════════════════════════════════════════════════════════╡");
        System.out.print(getPrintMultiLines("termin płatności: "+ getPaymentDeadline()));
        System.out.print(getPrintMultiLines("metoda płatności: "+ getPaymentMethod()));
        System.out.println("╞═════════════════════════════════════════════════════════════════════════════════════╡");
        System.out.println("│ Pozycje zamówienia                                                                  │");
        System.out.println("├─────┬────────────────────────────────┬───────┬─────────┬─────────┬────────┬─────────┤");
        System.out.println("│ lp. │ nazwa                          │ ilość │    cena │   netto │    ptu │  brutto │");
        System.out.println("├─────┼────────────────────────────────┼───────┼─────────┼─────────┼────────┼─────────┤");
        System.out.print(getPrintOrderItems());
        System.out.println("╘═════╧════════════════════════════════╧═══════╪═════════╪═════════╪════════╪═════════╡");
        System.out.println("                                               │ RAZEM   "+String.format("│ %1$7.2f │ %2$6.2f │ %3$7.2f │",
                getNetAmount(), getTaxAmount(), getGrossAmount()));
        System.out.println("                                               └─────────┴─────────┴────────┴─────────┘");

    }


    private String getPrintMultiLines(String textLines) {

        Scanner scanner = new Scanner(textLines);
        StringBuilder result = new StringBuilder();

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            result.append("│").append(String.format(" %-84s", line)).append("│").append("\n");
        }

        return result.toString();
    }

    private String getPrintOrderItems(){
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (OrderItemData item : orderItems){
            counter++;
            result.append(
                    String.format("│ %1$2d. │ %2$-30s │ %3$5d │ %4$7.2f │ %5$7.2f │ %6$6.2f │ %7$7.2f │\n",
                            counter,
                            item.getName(),
                            item.getQuantity(),
                            item.getGrossAmount(),
                            item.getNetAmount()*item.getQuantity(),
                            item.getTaxAmount()*item.getQuantity(),
                            item.getGrossAmount()*item.getQuantity()
                    ));

        }

        return result.toString();
    }
}
