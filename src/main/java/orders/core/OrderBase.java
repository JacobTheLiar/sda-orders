package orders.core;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class OrderBase implements Order {

    protected List<OrderItemData> orderItems;
    protected Customer issuedBy;
    protected Customer customer;
    protected String orderNumber;

    private int defferedDays;
    private LocalDateTime paymentDeadline;
    private LocalDateTime issueDate;
    private PaymentMethod paymentMethod;

    public OrderBase(Customer issuedBy, Customer customer, String orderNumber){

        this.issuedBy = issuedBy;
        this.customer = customer;
        this.orderNumber = orderNumber;

        orderItems = new ArrayList<>();
        issueDate = LocalDateTime.now();
        defferedDays = 0;
    }

    @Override
    public void addItem(OrderItemData item) {
        orderItems.add(item);
    }

    @Override
    public abstract void printOrder();

    public void deleteItem(int itemIndex) {

        orderItems.remove(itemIndex);

    }


    protected String getPaymentMethod() {
        return paymentMethod.toString();
    }

    protected String getPaymentDeadline() {

        StringBuilder string = new StringBuilder();
        string.append(TDateTimeToString.DateTimeToString(paymentDeadline, "yyyy-MM-dd"));

        if (defferedDays >0){
            string.append("; odroczono ").append(defferedDays).append(" dn.");
        }

        return string.toString();
    }

    protected String getIssueDate() {
        return TDateTimeToString.DateTimeToString(issueDate, "yyyy-MM-dd");
    }

    @Override
    public void setPaymentDeadline(PaymentMethod aPaymentMethod, int defferedDays) {
        issueDate = LocalDateTime.now();
        paymentDeadline = issueDate.plusDays(defferedDays);
        paymentMethod = aPaymentMethod;
    }


    @Override
    public String getOrderNumber() {
        return orderNumber;
    }

    @Override
    public int getItemsCount() {
        return orderItems.size();
    }

    @Override
    public OrderItemData getOrderItemData(int itemIndex) {
        return orderItems.get(itemIndex);
    }


    protected double getGrossAmount(){
        double amount = 0.0;
        for (OrderItemData item : orderItems)
            amount += item.getGrossAmount()*item.getQuantity();

        return amount;
    }

    protected double getNetAmount(){
        double amount = 0.0;
        for (OrderItemData item : orderItems)
            amount += item.getNetAmount()*item.getQuantity();

        return amount;
    }

    protected double getTaxAmount(){
        double amount = 0.0;
        for (OrderItemData item : orderItems)
            amount += item.getTaxAmount()*item.getQuantity();

        return amount;
    }

}
