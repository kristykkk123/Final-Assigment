package Repository;

import pojo.OrderPojo.OrderedProduct;
import com.google.gson.Gson;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingCartRepository {

    private static final String CART_FILE_PATH = "data/Cart.json";
    private static Gson gson = new Gson();
    private List<OrderedProduct> cart = new ArrayList<>();


    public static void saveCart(List<OrderedProduct> cartItems) {
        try {
            String jsonData = gson.toJson(cartItems);
            Files.write(Paths.get(CART_FILE_PATH), jsonData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


 public void saveCart() {
    try {
        String jsonData = gson.toJson(cart);
        Files.write(Paths.get(CART_FILE_PATH), jsonData.getBytes());
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public List<OrderedProduct> loadCart() {
    try {
        String jsonData = new String(Files.readAllBytes(Paths.get(CART_FILE_PATH)));
        OrderedProduct[] cartArray = gson.fromJson(jsonData, OrderedProduct[].class);
        cart = new ArrayList<>(Arrays.asList(cartArray));
    } catch (IOException e) {
        e.printStackTrace();
    }
    return cart;
}

    public void clear() {
        cart.clear();
        saveCart();
    }

    public void removeProductFromCartById(int productId) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getProductId() == productId) {
                cart.remove(i);
                break; 
            }
        } saveCart();
    }

    public void removeProductFromCartByName(String productName) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getProductName().equalsIgnoreCase(productName)) {
                cart.remove(i);
                break; }
            }
            saveCart();;
    }


}





