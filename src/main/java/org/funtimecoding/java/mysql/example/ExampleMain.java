package org.funtimecoding.java.mysql.example;
import java.sql.*;

public class ExampleMain {
    private static final String URL = "jdbc:mysql://database-1.czezznnjq1kk.us-east-1.rds.amazonaws.com:3306/testdb";  // Database URL
    private static final String USER = "admin";  // MySQL username
    private static final String PASSWORD = "ayushmanyan";  // MySQL password
    
    // Establish connection to MySQL
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // Create (Insert) a new user
    public void createUser(String name, String email) {
        String query = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("User created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Read (Select) a user by ID
    public void readUser(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Update a user's information
    public void updateUser(int id, String name, String email) {
        String query = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("User updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Delete a user by ID
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        ExampleMain dao = new ExampleMain();
        
        // Test CRUD operations
        dao.createUser("Mark Lee", "mark@example.com");
        dao.readUser(1);  // Assuming ID 1 exists
        dao.updateUser(1, "Mark Lee", "marklee@example.com");
        dao.deleteUser(2);  // Assuming ID 2 exists
    }
}
