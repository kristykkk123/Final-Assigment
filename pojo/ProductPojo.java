package pojo;

public class ProductPojo {

    private int productId;
    private String productName;
    private double productPrice;
    private String productionTime;

    public ProductPojo(int productId, String productName, double productPrice, String productionTime) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productionTime = productionTime; }
   

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }


    public void setProductionTime(String productionTime) {
        this.productionTime = productionTime;
        }

    public String getProductionTime() {
    return this.productionTime;
        } 

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;                   // Compare object references
            if (obj == null || getClass() != obj.getClass()) return false;  // Check null and class type
        
            ProductPojo product = (ProductPojo) obj;        // Cast the object to a ProductPojo
        
            return productId == product.productId;          // Compare the productIds
        }
        
        @Override
        public int hashCode() {
            return productId;  // Use productId as the hash code. 
        }
        
        @Override
        public String toString() {
        return "ProductPojo{" +
           "productId=" + productId +
           ", productName='" + productName + '\'' +
           ", productPrice=" + productPrice +
           ", productionTime='" + productionTime + '\'' +
           '}';
}

    }









    