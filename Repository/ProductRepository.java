package Repository;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import pojo.ProductPojo;

public class ProductRepository {

private Map<Integer, ProductPojo> productsById = new HashMap<>();
private Map<String, ProductPojo> productsByName = new HashMap<>();

public ProductRepository()
    {
        loadProductsfromCSV();
    } 
   
    private void loadProductsfromCSV() {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(ProductRepository.class.getResourceAsStream("/data/PriceList.csv")))) {
        reader.lines()
            .map(line -> line.split("\t+"))
            .forEach(parts -> {
                int productId = Integer.parseInt(parts[0].trim());
                String productName = parts[1].trim();
                double productPrice = Double.parseDouble(parts[2].trim());
                String productionTime = parts[3].trim();
                
                ProductPojo product = new ProductPojo(productId, productName, productPrice, productionTime);
                productsById.put(productId, product);
                productsByName.put(productName, product);
            });
    } catch (IOException e) {
        e.printStackTrace();
    }
}

//Files.lines: Reads the file and creates a Stream of lines.
////map: Transforms each line into an array of strings by splitting on one or more tabs.
//forEach: Processes each array of strings. For each array, it parses the values, creates a Product object, and then adds the product to a hypothetical productService (similar to the cashAccountService in the provided code).



public Map<Integer, ProductPojo> getProductsByIdMap() {
        return Collections.unmodifiableMap(productsById);
    }

    public Map<String, ProductPojo> getProductsByNameMap() {
        return Collections.unmodifiableMap(productsByName);
    }


























}