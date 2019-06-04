package orders.core;

public class CustomerImp implements Customer {

    private String name;
    private String address;
    private String zipCode;
    private String city;
    private String taxNumber;
    private String accountNumber;


    public CustomerImp(String name, String address, String zipCode, String city, String taxNumber,
                       String accountNumber){
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.taxNumber = taxNumber;
        this.accountNumber = accountNumber;
    }


    @Override
    public String getBillDescription() {

        StringBuilder bldr = new StringBuilder();

        bldr.append(name).append("\n");
        bldr.append(address).append("; ");
        bldr.append(zipCode).append(" ").append(city).append("\n");
        bldr.append("NIP: ").append(taxNumber).append("; Nr Konta: ").append(accountNumber);

        return bldr.toString();
    }

    @Override
    public String getName() {
        return name;
    }
}
