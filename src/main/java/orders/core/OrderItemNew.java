package orders.core;

public interface OrderItemNew {

    void setName(String aNazwa);
    void setGrossAmount(double aWartosc);
    void setQuantity(int aIlosc);

    OrderItemData getOrderItemData();
    OrderItemNew getOrderItemCopy();

}
