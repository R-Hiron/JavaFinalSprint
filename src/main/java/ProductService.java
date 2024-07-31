package src.main.java;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void addProduct(String name, double price, int quantity, int sellerId) throws SQLException {
        Product product = new Product(0, name, price, quantity, sellerId);
        productDAO.addProduct(product);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    public List<Product> getProductsBySellerId(int sellerId) throws SQLException {
        return productDAO.getProductsBySellerId(sellerId);
    }

    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int id) throws SQLException {
        productDAO.deleteProduct(id);
    }

    public List<Product> searchProductsByName(String name) throws SQLException {
        return productDAO.searchProductsByName(name);
    }
}