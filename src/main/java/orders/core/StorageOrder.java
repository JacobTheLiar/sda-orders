package orders.core;

import java.util.List;

public interface StorageOrder {

    List<Order> getOrderList();

    Order getOrder(int orderNumber);

    void deleteOrder(int orderNumber);

    void addOrder(Order order);

    int getOrderCount();



}
