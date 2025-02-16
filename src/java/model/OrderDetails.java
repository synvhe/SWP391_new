package model;

public class OrderDetails {
    private int orderId;
    private Product product;
    private int price;
    private int amount;
    
    public OrderDetails() {
    }
            

    public OrderDetails(int orderId, Product product, int price, int amount) {
        this.orderId = orderId;
        this.product = product;
        this.price = price;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderId=" + orderId + ", product=" + product + ", price=" + price + ", amount=" + amount + '}';
    }
    

    
}