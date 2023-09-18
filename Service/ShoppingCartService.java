package Service;

import pojo.CustomerPojo;
import pojo.OrderPojo;
import pojo.OrderPojo.OrderedProduct;
import pojo.ProductPojo;
import Repository.ShoppingCartRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;


    public ShoppingCartService() {
        this.shoppingCartRepository = new ShoppingCartRepository();
        shoppingCartRepository.loadCart();
    }

    // Add a product to the cart
    public void addProductToCart(OrderedProduct product) {
        shoppingCartRepository.loadCart().add(product);
        shoppingCartRepository.saveCart();
    }

    // Remove a product from the cart by its ID
    public void removeProductFromCartById(int productId) {
        shoppingCartRepository.removeProductFromCartById(productId);
    }

    // Remove a product from the cart by its name
    public void removeProductFromCartByName(String productName) {
        shoppingCartRepository.removeProductFromCartByName(productName);
    }


    public List<OrderedProduct> getCurrentCart() {
        return shoppingCartRepository.loadCart();
    }

    // Clear the cart
    public void clearCart() {
        shoppingCartRepository.clear();
        shoppingCartRepository.saveCart();

    }


    public OrderPojo createOrUpdateOrder(OrderPojo order, CustomerPojo customer) {
        order.setCustomerDetails(customer);
        return order;
    }

    public CustomerPojo createOrUpdateCustomerForOrder(OrderPojo order, CustomerPojo customer) {
        order.setCustomerDetails(customer);
        return customer;
    }

    // Modify the quantity of a product in the cart
    public void modifyItemQuantity(ProductPojo product, int newQuantity) {
    List<OrderedProduct> cartContents = shoppingCartRepository.loadCart();
    for (OrderedProduct orderedProduct : cartContents) {
        if (orderedProduct.getProduct().equals(product)) {
            double totalItemCost = product.getProductPrice() * newQuantity;
            orderedProduct.setQuantity(newQuantity);
            orderedProduct.setTotalItemCost(totalItemCost);
            break;
        }
    }
    shoppingCartRepository.saveCart(); // Store the modified cart back to the repository
}



    public OrderPojo generateOrderFromCart(CustomerPojo customer) {
        List<OrderedProduct> currentCartContents = getCurrentCart();

        if (currentCartContents.isEmpty()) {
            throw new IllegalStateException("Cannot generate order from an empty cart.");
        }

        String orderNumber = "ON" + System.currentTimeMillis();
        LocalDateTime orderDate = LocalDateTime.now();

        
        int totalProductionTimeInMinutes = currentCartContents.stream()
        .mapToInt(product -> {
            String[] parts = product.getProduct().getProductionTime().split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            return hours * 60 + minutes;  // This will give production time in minutes for each product
        })
        .sum();
    
         int totalProductionTimeInHours = totalProductionTimeInMinutes / 60;

        LocalDateTime pickupDateTime = orderDate.plusHours(totalProductionTimeInHours);

        double totalOrderCost = currentCartContents.stream()
            .mapToDouble(OrderedProduct::getTotalItemCost)
            .sum();

        OrderPojo order = new OrderPojo(orderNumber, orderDate , currentCartContents, totalProductionTimeInHours, pickupDateTime, totalOrderCost);
        order.setCustomerDetails(customer);

        clearCart();

        return order;
    }

}









