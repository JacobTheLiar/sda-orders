package orders.core;

public interface Order {

    void addItem(OrderItemData item);
    void deleteItem(int itemIndex);
    void printOrder();

    void setPaymentDeadline(PaymentMethod paymentMethod, int defferedDays);
    String getOrderNumber();
    int getItemsCount();
    OrderItemData getOrderItemData(int itemIndex);

}
