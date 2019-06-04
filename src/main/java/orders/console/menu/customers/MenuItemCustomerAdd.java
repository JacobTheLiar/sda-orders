package orders.console.menu.customers;

import orders.console.menu.item.MenuItem;
import orders.console.menu.item.MenuItemBase;
import orders.console.menu.item.Message;
import orders.console.menu.item.MessageConsole;
import orders.console.retrieve.Retrieve;
import orders.console.retrieve.RetrieveBankAccountNumber;
import orders.console.retrieve.RetrieveTaxNumber;
import orders.console.retrieve.RetrieveZipCode;
import orders.core.Customer;
import orders.core.CustomerImp;
import orders.core.StorageCustomer;

import static orders.console.retrieve.Input.clearConsole;
import static orders.console.retrieve.Input.getString;

public class MenuItemCustomerAdd extends MenuItemBase implements MenuItem {
    private final StorageCustomer customers;

    private String name = "";
    private String address = "";
    private String zip = "";
    private String city = "";
    private String tax = "";
    private String account = "";
    private Customer customer;


    public MenuItemCustomerAdd(StorageCustomer customers) {
        super("dodaj kontrahenta");
        this.customers = customers;
    }

    @Override
    public void execte() {

        getName();
        getAddress();
        getZipCode();
        getCity();
        getTaxNumber();
        getBankAccountNumber();

        addCustomer();
        printMessage();

    }

    private void printMessage() {
        Message msg = new MessageConsole();
        msg.showMessage("informacja", "dodano kontrahenta \""+customer.getName()+"\"");
    }

    private void addCustomer() {
        customer = new CustomerImp(name, address, zip, city, tax, account);
        customers.addCustomer(customer);
    }

    private void getBankAccountNumber() {
        printOption("podaj numer konta bankowego");
        Retrieve retrieve = new RetrieveBankAccountNumber();
        account = retrieve.getData();
    }

    private void getTaxNumber() {
        printOption("podaj numer NIP");
        Retrieve retrieve = new RetrieveTaxNumber();
        tax = retrieve.getData();
    }

    private void getCity() {
        printOption("podaj nazwe miasta");
        city = getValue();
    }

    private void getZipCode() {
        printOption("podaj kod pocztowy");
        Retrieve retrieve = new RetrieveZipCode();
        zip = retrieve.getData();

    }

    private void getName() {
        printOption("podaj nazwę firmy");
        name = getValue();
    }

    private void getAddress() {
        printOption("podaj adres");
        address = getValue();
    }

    private void printOption(String optionName){
        printCustomerData();
        System.out.print(String.format("│ %-50s │\n", optionName));

    }


    private String getValue(){
        System.out.print("│");
        return getString();
    }


    private void printCustomerData(){
        clearConsole();
        StringBuilder builder = new StringBuilder();

        builder.append("┌────────────────────────────────────────────────────┐\n");
        builder.append(String.format("│ %50s │\n", "dodawanie nowego kontrahenta"));
        builder.append("╞═════════╤══════════════════════════════════════════╡\n");
        builder.append("│ pozycja │ wartość                                  │\n");
        builder.append("├─────────┼──────────────────────────────────────────┤\n");
        builder.append(String.format("│ nazwa   │ %-40s │\n", name));
        builder.append(String.format("│ adres   │ %-40s │\n", address));
        builder.append(String.format("│         │ %-40s │\n", zip+" "+city));
        builder.append(String.format("│ NIP     │ %-40s │\n", tax));
        builder.append(String.format("│ konto   │ %-40s │\n", account));
        builder.append("├─────────┴──────────────────────────────────────────┤\n");

        System.out.print(builder);
    }

}
