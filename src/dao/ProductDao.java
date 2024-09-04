package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Product;
import dbconnection.DBConnection;

public class ProductDao {

    // Method to insert a new product
    public boolean insertProduct(Product product) {
        String query = "INSERT INTO products (product_name, collection_id, collection_name, price, image_url, description, material_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getCollectionId());
            
            // Fetch collection name based on collection_id
            String collectionName = getCollectionNameById(product.getCollectionId());
            if (collectionName == null) {
                throw new SQLException("Collection name not found for ID: " + product.getCollectionId());
            }
            statement.setString(3, collectionName);
            statement.setInt(4, product.getPrice());
            statement.setString(5, product.getImageUrl());
            statement.setString(6, product.getDescription());
            statement.setInt(7, product.getMaterialId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Helper method to get collection name by ID
    private String getCollectionNameById(int collectionId) {
        String query = "SELECT collection_name FROM collections WHERE collections_Id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, collectionId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("collection_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to retrieve all products
    public List<Product> getAllProducts() {
        String query = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setCollectionId(resultSet.getInt("collection_id"));
                product.setCollectionName(resultSet.getString("collection_name"));
                product.setPrice(resultSet.getInt("price"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setDescription(resultSet.getString("description"));
                product.setMaterialId(resultSet.getInt("material_id")); // Added field

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // Method to retrieve a product by ID
    public Product getProductById(int productId) {
        String query = "SELECT * FROM products WHERE id = ?";
        Product product = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setCollectionId(resultSet.getInt("collection_id"));
                product.setCollectionName(resultSet.getString("collection_name"));
                product.setPrice(resultSet.getInt("price"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setDescription(resultSet.getString("description"));
                product.setMaterialId(resultSet.getInt("material_id")); // Added field
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    // Method to update an existing product
    public boolean updateProduct(Product product) {
        String query = "UPDATE products SET product_name = ?, collection_name = ?, price = ?, image_url = ?, description = ?, material_id = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, product.getProductName());
            statement.setString(2, product.getCollectionName());
            statement.setInt(3, product.getPrice());
            statement.setString(4, product.getImageUrl());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getMaterialId()); // Added field
            statement.setInt(7, product.getId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Method to delete a product by ID
    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, productId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
