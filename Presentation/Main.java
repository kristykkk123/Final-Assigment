package Presentation;
import java.util.List;
import java.util.Optional;

import pojo.*;
import pojo.OrderPojo.OrderedProduct;
import Service.*;
import java.util.Scanner;

import Repository.*;


public class Main {

    private static CustomerPojo currentCustomer;
    private static int customerCounter = 0;  // This will be used to auto-increment the customer ID

    private static ProductService productService = new ProductService();
    private static ShoppingCartService shoppingCartService = new ShoppingCartService();
    private static OpeningHoursRepository openingHoursRepo = new OpeningHoursRepository();
    private static InvoiceService invoiceService = new InvoiceService(openingHoursRepo);
    private static ProductRepository productRepository = new ProductRepository();
    

    private static Scanner scanner = new Scanner(System.in);


public static void main(String[] args) {
    boolean continueRunning = true;

    while (continueRunning) {
        System.out.println("Welcome to PhotoShop!");
        System.out.println("Please choose an option:");
        System.out.println("0. Show all products");
        System.out.println("1. Add Items to Order");
        System.out.println("2. Modify Current Cart");
        System.out.println("3. Add/Modify Customer Details");
        System.out.println("4. View Order");
        System.out.println("5. Checkout");
        System.out.println("6. Review Old orders");
        System.out.println("7. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 0:
            displayAllProducts();
                break;
            case 1:
                manageOrderItems();
                break;
            case 2:
                manageCart();
                break;
            case 3:
                inputOrModifyCustomerDetails();
                break;
            case 4:
                viewCurrentOrder();
                break;
            case 5:
                checkout();
                break;
            case 6:
                ReviewOldOrders();
                break;
            case 7:
                System.out.println("Thank you for using PhotoShop!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}


private static void manageOrderItems() {
    System.out.println("1 Add item by ID");
    System.out.println("2 Add item by Name");
    
    int choice = scanner.nextInt();
    scanner.nextLine();  // Consume newline

    switch (choice) {
        case 1:
             System.out.println("Enter product ID to add:");
            int productId = scanner.nextInt();
            scanner.nextLine();
            
            Optional<ProductPojo> productById = productService.getProductById(productId);

            if (productById.isPresent()) {
                System.out.println("Enter the quantity for the product:");
                int quantity = scanner.nextInt();
                scanner.nextLine();  // consume newline
                
                double totalItemCost = productById.get().getProductPrice() * quantity; 
                
                OrderedProduct orderedProduct = new OrderedProduct(productById.get(), quantity, totalItemCost);
                
                shoppingCartService.addProductToCart(orderedProduct);
            } else {
                System.out.println("Product with given ID not found.");
            } break;
           
            
        case 2:
        System.out.println("Enter product name to add:");
        String productName = scanner.nextLine();
    
        Optional<ProductPojo> productByName = productService.getProductByName(productName);
    
        if (productByName.isPresent()) {
            System.out.println("Enter the quantity for the product:");
            int quantity = scanner.nextInt();
            scanner.nextLine();  // consume newline
    
            double totalItemCost = productByName.get().getProductPrice() * quantity; 
    
            OrderPojo.OrderedProduct orderedProduct = new OrderPojo.OrderedProduct(productByName.get(), quantity, totalItemCost);
            
            shoppingCartService.addProductToCart(orderedProduct);
    
            System.out.println("Product added to cart successfully!");
        } else {
            System.out.println("Product with given name not found.");
        }
        break;
        default:
                System.out.println("Invalid choice. Please try again.");        
      
} } 


    private static void manageCart() {
        System.out.println("2.1 Modify item quantity");
        System.out.println("2.2 Remove item");
        int subChoice = scanner.nextInt();
        scanner.nextLine();

    switch (subChoice) {
        case 1:
        System.out.println("Enter product ID or Name of the item you want to modify:");
        String input = scanner.nextLine();

        // Check if input is numeric or a string
        if (isNumeric(input)) {
            int productId = Integer.parseInt(input);
            Optional<ProductPojo> productById = productService.getProductById(productId);
            if (productById.isPresent()) {
                System.out.println("Enter the new quantity for the product:");
                int quantity = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                shoppingCartService.modifyItemQuantity(productById.get(), quantity);
            } else {
                System.out.println("Product with given ID not found.");
            }
        } else {
            Optional<ProductPojo> productByName = productService.getProductByName(input);
            if (productByName.isPresent()) {
                System.out.println("Enter the new quantity for the product:");
                int quantity = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                shoppingCartService.modifyItemQuantity(productByName.get(), quantity);
            } else {
                System.out.println("Product with given name not found.");
            }
        }
        break;
           
        case 2: // Remove item
        System.out.println("Enter product ID or Name of the item you want to remove:");
        String itemToRemove = scanner.nextLine();
        if (isNumeric(itemToRemove)) {
            int productId = Integer.parseInt(itemToRemove);
            shoppingCartService.removeProductFromCartById(productId);
        } else {
            shoppingCartService.removeProductFromCartByName(itemToRemove);
        }
        break;
        default:
            System.out.println("Invalid choice. Please try again.");
    }
}

private static void inputOrModifyCustomerDetails() {
        if (currentCustomer != null) {
            System.out.println("Current Customer Details:");
            System.out.println("Id: " + currentCustomer.getId());
            System.out.println("Name: " + currentCustomer.getName());
            System.out.println("Address: " + currentCustomer.getAddress());
            System.out.println("Phone: " + currentCustomer.getPhoneNumber());
    
            System.out.println("Would you like to modify these details? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
    
            if (!response.equals("yes")) {
                return;  // If the user doesn't want to modify, we simply return.
            }
        }
    
        System.out.println("Enter Customer Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Customer Address:");
        String address = scanner.nextLine();
        System.out.println("Enter Customer Phone Number:");
        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid phone number. Please enter a valid number.");
            return;
        } 
        // If currentCustomer is not null, we're modifying an existing customer. So, we reuse its ID.
    int customerId = (currentCustomer != null) ? currentCustomer.getId() : customerCounter;

    currentCustomer = new CustomerPojo(customerId, name, address, phoneNumber);  // This will either set a new customer or modify the existing one.
    System.out.println("Customer details saved/updated!");
}
    

        private static void viewCurrentOrder() {
    
        List<OrderedProduct> currentOrder = shoppingCartService.getCurrentCart();
        
        if (currentOrder.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Current items in your cart:");
            for (OrderedProduct orderedProduct : currentOrder) {
                ProductPojo product = orderedProduct.getProduct();
                System.out.println("Product Name: " + product.getProductName());
                System.out.println("Quantity: " + orderedProduct.getQuantity());
                System.out.println("Price per item: " + product.getProductPrice());
                System.out.println("Total cost of item: " + orderedProduct.getTotalItemCost());
                System.out.println("----------");
            }
        }
    }
    

private static void checkout() {
     // Step 1: Convert cart to order
     OrderPojo order = shoppingCartService.generateOrderFromCart(currentCustomer);

     // Step 2: Generate invoice for the order
     InvoicePojo invoice = invoiceService.generateInvoiceForOrder(order);

     // Step 3: Confirm and display order details to the customer
     displayOrderConfirmation(order, invoice);
 }


 private static void displayOrderConfirmation(OrderPojo order, InvoicePojo invoice) {
    System.out.println("Order Confirmation");
    System.out.println("==================");
    System.out.println("Invoice Number: " + invoice.getInvoiceNumber());
    System.out.println("==================");
    System.out.println("Customer Id: " + invoice.getCustomerInfo().getId());
    System.out.println("Customer Name: " + invoice.getCustomerInfo().getName());
    System.out.println("Customer Address: " + invoice.getCustomerInfo().getAddress());
    System.out.println("Customer Phone Number: " + invoice.getCustomerInfo().getPhoneNumber());
    System.out.println("==================");
    System.out.println("Order Number: " + order.getOrderNumber());
    System.out.println("Order Date: " + order.getFormattedOrderDate());
    System.out.println("Estimated Pickup Time: " + order.getFormattedPickupDateTime());
    System.out.println("==================");
    System.out.println("Ordered Products:");
    
    for(OrderPojo.OrderedProduct orderedProduct : order.getOrderedProducts()) {
        ProductPojo product = orderedProduct.getProduct();
        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Product Price: " + product.getProductPrice());
        System.out.println("Quantity: " + orderedProduct.getQuantity());
        System.out.println("Total Item Cost: " + orderedProduct.getTotalItemCost());
        System.out.println("------------------");
    }
    System.out.println("Total Order Cost: " + order.getTotalOrderCost());
}

      
            

private static boolean isNumeric(String str) {
    try {
        Double.parseDouble(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

private static void displayAllProducts() {
    List<ProductPojo> allProducts = productService.getAllProducts();
    
    if (allProducts.isEmpty()) {
        System.out.println("No products available.");
    } else {
        System.out.println("Available products:");
        for (ProductPojo product : allProducts) {
            System.out.println("--------------------------");
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Price: $" + product.getProductPrice());
            System.out.println("Production Time: " + product.getProductionTime());
        }
        System.out.println("--------------------------");
    }
}

 public static void ReviewOldOrders(){
    
 }



}








