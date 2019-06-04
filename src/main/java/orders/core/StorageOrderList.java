package orders.core;

import java.util.ArrayList;
import java.util.List;

public class StorageOrderList implements StorageOrder {


    private List<Order> orderList;

    public StorageOrderList(){
        orderList = new ArrayList<>();
    }

    @Override
    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public Order getOrder(int orderNumber) {
        return orderList.get(orderNumber);
    }

    @Override
    public void deleteOrder(int orderNumber) {
        orderList.remove(orderNumber);
    }

    @Override
    public void addOrder(Order order) {
        orderList.add(order);
    }

    @Override
    public int getOrderCount() {
        return orderList.size();
    }
}
