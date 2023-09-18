package Service;

import pojo.ProductPojo;
import Repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    /**
     * Fetch a product by its ID.
     * 
     * @param id The ID of the product.
     * @return An Optional containing the product if found, or an empty Optional otherwise.
     */
    public Optional<ProductPojo> getProductById(int id) {
        return Optional.ofNullable(productRepository.getProductsByIdMap().get(id));
    }

    /**
     * Fetch a product by its name.
     * 
     * @param name The name of the product.
     * @return An Optional containing the product if found, or an empty Optional otherwise.
     */
    public Optional<ProductPojo> getProductByName(String name) {
        return Optional.ofNullable(productRepository.getProductsByNameMap().get(name));
    }

    //this method fetches all products from the repository and returns them as a list. 
    public List<ProductPojo> getAllProducts() {
        return productRepository.getProductsByIdMap().values().stream().collect(Collectors.toList());
    }

    
}