package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MaterialBean;  // Ensure this matches your package
import dbconnection.DBConnection;  // Ensure this matches your package

public class MaterialDao {

	public List<MaterialBean> getAllMaterials() {
        List<MaterialBean> materials = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Get a connection to the database
            conn = DBConnection.getConnection();
            
            // SQL query to fetch all materials
            String sql = "SELECT * FROM materials";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Iterate over the result set and add materials to the list
            while (rs.next()) {
                MaterialBean material = new MaterialBean();
                material.setId(rs.getInt("id"));
                material.setMaterialName(rs.getString("material_name"));
                materials.add(material);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return materials;
    }


    public MaterialBean getMaterialById(int id) throws SQLException {
    	MaterialBean material = null;
        String query = "SELECT * FROM materials WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    material = new MaterialBean();
                    material.setId(resultSet.getInt("id"));
                    material.setMaterialName(resultSet.getString("material_name"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception in getMaterialById: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return material;
    }

    private Connection getConnection() throws SQLException {
        // Replace with your database connection details
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "root";
        String password = "password";
        return DriverManager.getConnection(url, user, password);
    }

    public void addMaterial(MaterialBean material) throws SQLException {
        String sql = "INSERT INTO materials (material_name, material_type) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getMaterialName());
            stmt.setString(2, material.getMaterialType());
            stmt.executeUpdate();
        }
    }

    public boolean updateMaterial(MaterialBean material) throws SQLException {
        String query = "UPDATE materials SET material_name = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, material.getMaterialName());
            statement.setInt(2, material.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("SQL Exception in updateMaterial: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public boolean deleteMaterial(int id) throws SQLException {
        String query = "DELETE FROM materials WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("SQL Exception in deleteMaterial: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
