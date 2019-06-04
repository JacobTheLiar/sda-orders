package orders.core;

import java.util.List;

public interface StorageCustomer {

    List<Customer> getCustomerList();
    Customer getCustomer(int customerId);
    void deleteCustomer(int customerId);
    void addCustomer(Customer customer);
    int getCustomerCount();


}
