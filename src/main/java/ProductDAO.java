import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, quantity, seller_id) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setInt(4, product.getSellerId());
        statement.executeUpdate();
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            int sellerId = resultSet.getInt("seller_id");
            products.add(new Product(id, name, price, quantity, sellerId));
        }
        return products;
    }

    public List<Product> getProductsBySellerId(int sellerId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE seller_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, sellerId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            products.add(new Product(id, name, price, quantity, sellerId));
        }
        return products;
    }

    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setInt(4, product.getId());
        statement.executeUpdate();
    }

    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public List<Product> searchProductsByName(String name) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String productName = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            int sellerId = resultSet.getInt("seller_id");
            products.add(new Product(id, productName, price, quantity, sellerId));
        }
        return products;
    }
}
