package orders.core;

import java.util.ArrayList;
import java.util.List;

public class StorageCustomerList implements StorageCustomer {

    private List<Customer> customerList;

    public StorageCustomerList() {
        customerList = new ArrayList<>();
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerList;
    }

    @Override
    public Customer getCustomer(int customerId) {
        return customerList.get(customerId);
    }

    @Override
    public void deleteCustomer(int customerId) {
        customerList.remove(customerId);
    }

    @Override
    public void addCustomer(Customer customer) {

        if (!customerList.contains(customer))
          customerList.add(customer);
    }

    @Override
    public int getCustomerCount() {
        return customerList.size();
    }
}
