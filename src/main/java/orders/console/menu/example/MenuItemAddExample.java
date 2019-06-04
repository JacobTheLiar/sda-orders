package orders.console.menu.example;

import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemBase;
import orders.console.menu.item.Message;
import orders.console.menu.item.MessageConsole;
import orders.console.menu.print.OrderImp;
import orders.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuItemAddExample extends MenuItemBase implements MenuItem {

    private final StorageCustomer storageCustomer;
    protected StorageOrder storageOrder;
    private final Customer issueBy;

    private final List<Customer> customerList;
    private final List<OrderItemNew> orderItemList;

    public MenuItemAddExample(StorageOrder storageOrder, Customer issueBy, StorageCustomer storageCustomer) {
        super("dodaj przykładowe zamówienie");
        this.storageOrder = storageOrder;
        this.storageCustomer = storageCustomer;
        this.issueBy = issueBy;

        OrderItemNew orderItem;
        orderItemList = new ArrayList<>();

        orderItem = new OrderItem("bilet turystyczny", 34.98);
        orderItemList.add(orderItem);
        orderItem = new OrderItem("bilet jednorazowy", 1.98);
        orderItemList.add(orderItem);
        orderItem = new OrderItem("opłata za wydanie", 8.00);
        orderItemList.add(orderItem);
        orderItem = new OrderItem("przewóz roweru", 3.45);
        orderItemList.add(orderItem);
        orderItem = new OrderItem("bilet miesięczny", 17.98);
        orderItemList.add(orderItem);
        orderItem = new OrderItem("przewóz bagaż", 4.68);
        orderItemList.add(orderItem);
        orderItem = new OrderItem("przewóz psa", 2.34);
        orderItemList.add(orderItem);
        orderItem = new OrderItem("bilet ulgowy", 12.66);
        orderItemList.add(orderItem);


        Customer customer;
        customerList = new ArrayList<>();

        customer = new CustomerImp("SDA Sp. z o. o.", "ul. Zwycięstwa 96/98",
                "85-451", "Gdynia", "PL5842741225", "PL40 1140 1153 0000 4241 4900 1001");
        customerList.add(customer);
        customer = new CustomerImp("TABAK KRAKÓW Barbara i Piotr Gigoń s.c.", "ul.Chałubińskiego 68",
                "30-698", "Kraków", "PL8566327546", "PL45 1240 2715 5054 1917 5311 4182");
        customerList.add(customer);
        customer = new CustomerImp("Trafika Nord", "Swobodna 38",
                "15-756", "Białystok", "PL0711077617", "PL62 1240 5556 7413 1238 1533 3552");
        customerList.add(customer);
        customer = new CustomerImp("BSC Polska Sp. z o.o.", "ul. E. Schroegera 32",
                "01-822", "Warszawa", "PL0711077617", "PL62 1240 5556 7413 1238 1533 3552");
        customerList.add(customer);

    }

    @Override
    public void execte(){

        Random random = new Random();

        Customer customer = customerList.get(random.nextInt(customerList.size()));
        storageCustomer.addCustomer(customer);
        Order order = new OrderImp(issueBy, customer, (random.nextInt(900)+100)+ "/2019");
        order.setPaymentDeadline(PaymentMethod.BANK_TRANSFER, 14);

        int orderCount = random.nextInt(30);

        for (int i=0; i<orderCount; i++){
            OrderItemNew item = orderItemList.get(random.nextInt(orderItemList.size())).getOrderItemCopy();
            item.setQuantity(random.nextInt(10)+1);
            order.addItem(item.getOrderItemData());
        }

        storageOrder.addOrder(order);

        printSuccess(order);
    }

    private void printSuccess(Order order) {
        Message msg = new MessageConsole();
        msg.showMessage("dodano przykładowe zamówienie", "numer zamówienia "+order.getOrderNumber());
    }

}
