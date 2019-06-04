package orders.core;

public interface OrderItemData {

    String getName();
    double getGrossAmount();
    double getNetAmount();
    double getTaxAmount();
    int getQuantity();

}
