package pojo;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderPojo{
   
    private String orderNumber;
    private LocalDateTime orderDate;
    private List<OrderedProduct> orderedProducts;
    private int totalProductionTime;
    private LocalDateTime pickupDateTime;
    private double totalOrderCost;
    private CustomerPojo customerDetails;

    public OrderPojo(String orderNumber, LocalDateTime orderDate, List<OrderedProduct> orderedProducts, int totalProductionTime, LocalDateTime pickupDateTime, double totalOrderCost) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderedProducts = orderedProducts;
        this.totalProductionTime = totalProductionTime;
        this.pickupDateTime = pickupDateTime;
        this.totalOrderCost = totalOrderCost;}
    
        public String getFormattedOrderDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return orderDate.format(formatter);
    }

    public String getFormattedPickupDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return pickupDateTime.format(formatter);
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderedProduct> getOrderedProducts() {
        return this.orderedProducts;
    }

    public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public int getTotalProductionTime() {
        return this.totalProductionTime;
    }

    public void setTotalProductionTime(int totalProductionTime) {
        this.totalProductionTime = totalProductionTime;
    }

    public LocalDateTime getPickupDateTime() {
        return this.pickupDateTime;
    }

    public void setPickupDateTime(LocalDateTime pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public double getTotalOrderCost() {
        return this.totalOrderCost;
    }

    public void setTotalOrderCost(double totalOrderCost) {
        this.totalOrderCost = totalOrderCost;
    }
    
    public CustomerPojo getCustomerDetails() {
        return this.customerDetails;
    }

    public void setCustomerDetails(CustomerPojo customerDetails) {
        this.customerDetails = customerDetails;
    }

    public static class OrderedProduct {
        private ProductPojo product;
        private int quantity;
        private double totalItemCost;

    public OrderedProduct(ProductPojo product, int quantity, double totalItemCost) {
        this.product = product;
        this.quantity = quantity;
        this.totalItemCost = totalItemCost;
    }

    public ProductPojo getProduct() {
        return this.product;
    
    }

    public void setProduct(ProductPojo product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemCost() {
        return this.totalItemCost;
    }

    public void setTotalItemCost(double totalItemCost) {
        this.totalItemCost = totalItemCost;
    
    }
}

@Override
public String toString() {
    // Customize this to format the order details for the invoice.
    return "Order Number: " + orderNumber + 
           "\nDate: " + orderDate +
           "\nProducts: " + orderedProducts +
           "\nTotal Cost: " + totalOrderCost +
           "\nPickup Time: " + pickupDateTime;
}
}


    












