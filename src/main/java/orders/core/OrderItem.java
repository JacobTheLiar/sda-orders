package orders.core;

public class OrderItem implements OrderItemData, OrderItemNew {

    private String name;
    private double grossAmount;
    private int quantity;
    private float TAX_VALUE = 0.08f;

    public OrderItem(String name, double grossAmount, int quantity) {
        this.name = name;
        if (grossAmount>0.0)
            this.grossAmount = grossAmount;
        else
            this.grossAmount = 0.0f;

        if (quantity>0)
            this.quantity = quantity;
        else
            this.quantity =1;
    }

    public OrderItem(String name, double grossAmount) {
        this(name, grossAmount, 1);
    }

    public OrderItem() {
        this("bez nazwy", 0.0f);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getGrossAmount() {
        return grossAmount;
    }

    @Override
    public double getNetAmount() {
        return grossAmount - getTaxAmount();
    }

    @Override
    public double getTaxAmount() {
        return (grossAmount * TAX_VALUE)/(1- TAX_VALUE);
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setName(String name) {
        if (name.isEmpty())
            this.name = "bez nazwy";
         else
            this.name = name;
    }

    @Override
    public void setGrossAmount(double grossAmount) {
        if (grossAmount>=0.0f)
            this.grossAmount = grossAmount;
        else {
            this.grossAmount = 0.0f;
            throw new IllegalArgumentException("cena nie możne być ujemna! przypisano 0,00.");
        }
    }

    @Override
    public void setQuantity(int quantity) {
        if (quantity>0)
            this.quantity = quantity;
        else {
            this.quantity = 1;
            throw new IllegalArgumentException("ilośc nie może być ujemna! przypisano 1.");
        }
    }

    @Override
    public OrderItemData getOrderItemData() {
        return this;
    }

    @Override
    public OrderItemNew getOrderItemCopy() {
        OrderItemNew newItem = new OrderItem(name, grossAmount);
        return newItem;
    }
}
