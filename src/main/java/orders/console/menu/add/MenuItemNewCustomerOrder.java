package orders.console.menu.add;

import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemBase;
import orders.console.menu.item.Message;
import orders.console.menu.item.MessageConsole;
import orders.console.menu.print.OrderImp;
import orders.core.*;

import java.util.Random;

import static orders.console.retrieve.Input.*;

public class MenuItemNewCustomerOrder extends MenuItemBase implements MenuItem {


    private final Customer customer;
    private final Customer issueBy;
    private final StorageOrder storageOrder;
    private Order order;

    private PaymentMethod paymentMethod = PaymentMethod.CASH;
    private int defferedDays = 0;

    public MenuItemNewCustomerOrder(String title, Customer customer, Customer issueBy, StorageOrder storageOrder) {
        super(title);
        this.customer = customer;
        this.issueBy = issueBy;
        this.storageOrder = storageOrder;
    }

    @Override
    public void execte() {

        Random random = new Random();
        String orderNr = (random.nextInt(900)+100)+ "/2019";

        order = new OrderImp(issueBy, customer, orderNr);

        paymentMethod = getPaymentMethod();
        defferedDays = getDefferedDays();
        order.setPaymentDeadline(paymentMethod, defferedDays);

        getOrderPositions();

        storageOrder.addOrder(order);
        printMessage();
    }

    private void printMessage() {
        Message msg = new MessageConsole();
        msg.showMessage("nowe zamówienie", "dodano nowe zamówienie nr"+order.getOrderNumber());
    }

    private void getOrderPositions() {

        printOption("dodawanie pierwszej pozycji zamówienia");
        //dodanie pierwszego zamówienia
        order.addItem(getNewOrderItem());

        //dodanie kolejnych zamówień
        boolean exit;
        do{
            printOption("dodawanie pozycji zamówienia");
            if (order.getItemsCount()>0)
                System.out.println("│    0 - zakończ dodawanie pozycji");
            System.out.println("│    1 - dodaj nową pozycję");
            if (order.getItemsCount()>0)
                System.out.println("│    2 - usuń pozycję zamówienia");
            System.out.print("│ wybierz opcję");
            int answer = getInt();
            if (answer==1)
                order.addItem(getNewOrderItem());
            else if (answer==2 && order.getItemsCount()>0)
                deleteOrder();
            else if (answer!=0)
                System.out.println("│ wybór z poza zakresu, spróbuj ponownie.");

            exit = answer==0;
        }while (!exit);
    }

    private void deleteOrder() {
        printOption("podaj numer pozycji do usunięcia (0 - anulowanie operacji)");
        int index;
        do{
            index = getInt();
            if (!(0<= index && index <order.getItemsCount()))
                System.out.println("│ wybór z poza zakresu, spróbuj ponownie.");
        }while (!(0<= index && index <=order.getItemsCount()));

        if (index>0)
            order.deleteItem(index-1);

    }

    private OrderItemData getNewOrderItem() {
        printOption("dodawanie nowej pozycji zamówienia");

        String name = getOrderItemName();
        int quantity = getQuantity();
        double grossValue = getGrossValue();

        OrderItemNew orderItem = new OrderItem(name, grossValue, quantity);

        return orderItem.getOrderItemData();
    }

    private String getOrderItemName() {
        String result;
        do{
            System.out.print("│ podaj nazwe produktu/usługi\n│");
            result = getString();
            if (result.isEmpty())
                System.out.print("│ nazwa nie może być pusta\n│");

        } while (result.isEmpty());
        return result;
    }

    private double getGrossValue() {
        printOption("wartość brutto jednej sztuki", false);
        double result;
        do{
            result = getDouble();
            if (result<0.0)
                System.out.print("│ wartość brutto nie może być ujemna\n│");
        }while(result<0.0);

        return result;
    }

    private int getQuantity() {
        printOption("podaj ilość", false);
        int result;
        do {
            result = getInt();
            if (result<=0)
                System.out.print("│ ilość nie może być ujemna lub zerowa\n│");
        }while (result<=0);

        return result;
    }

    private int getDefferedDays() {
        printOption("podaj liczbę dni odroczenia płatności", false);
        int result;
        do{
            result = getInt();
            if (result<0)
                System.out.println("│ liczba musi być dni większa lub równa 0");
        } while (result<0);
        return result;
    }

    private PaymentMethod getPaymentMethod() {
        printOption("wybierz metodę płatności");


        int index;
        boolean exit;
        do {
            int counter = 0;
            for (PaymentMethod paymentMethod : PaymentMethod.values())
            {
                counter++;
                System.out.printf("│   %d - %s\n", counter, paymentMethod);
            }

            System.out.print("│ podaj metodę (1-"+counter+")");
            index = getInt()-1;
            exit = (0<=index && index<PaymentMethod.values().length);

            if (!exit)
                System.out.println("│ pozycja z poza zakresu, spróbuj ponownie");

        } while(!exit);

        return PaymentMethod.values()[index];
    }

    private void printOption(String optionName){
        printOption(optionName, true);
    }

    private void printOption(String optionName, boolean refresh){
        if (refresh)
            printOrderData();
        System.out.print(String.format("│ %-51s │\n", optionName));
    }

    private void printOrderData(){

        clearConsole();
        StringBuilder builder = new StringBuilder();

        builder.append("┌─────────────────────────────────────────────────────┐\n");
        builder.append(String.format("│ %51s │\n", "dodawanie nowego zamówienia"));
        builder.append("╞══════════╤══════════════════════════════════════════╡\n");
        builder.append("│ pozycja  │ wartość                                  │\n");
        builder.append("├──────────┼──────────────────────────────────────────┤\n");
        builder.append(String.format("│ nr zam.  │ %-40s │\n", order.getOrderNumber()));
        builder.append(String.format("│ odbiorca │ %-40s │\n", customer.getName()));
        builder.append(String.format("│ met. pł. │ %-40s │\n", paymentMethod.toString()));
        builder.append(String.format("│ odrocz.  │ %-40s │\n", Integer.toString(defferedDays)));
        builder.append(String.format("│          │ %-40s │\n", ""));
        builder.append(String.format("│ pozycje  │ %-40s │\n", "ilość: "+order.getItemsCount()));

        for (int i=0;i<order.getItemsCount();i++){
            OrderItemData item = order.getOrderItemData(i);
            builder.append(String.format("│  %6s. │ %-40s │\n", Integer.toString(i+1), item.getQuantity() +"x "+item.getName()));

        }

        builder.append("├──────────┴──────────────────────────────────────────┤\n");

        System.out.print(builder);
    }
}
