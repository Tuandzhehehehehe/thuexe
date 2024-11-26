import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DBUtils {

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/rentcar";
        String username = "anbu";
        String password = "batmankhongmocoi";
        return DriverManager.getConnection(url, username, password);
    }

    // Method to execute update queries (e.g., INSERT, UPDATE, DELETE)
    public static int executeUpdate(PreparedStatement pstmt, Object... params) throws SQLException {
        setParameters(pstmt, params);
        return pstmt.executeUpdate();
    }

    // Method to execute select queries
    public static ResultSet executeQuery(PreparedStatement pstmt, Object... params) throws SQLException {
        setParameters(pstmt, params);
        return pstmt.executeQuery();
    }

    // Method to set parameters for a PreparedStatement
    public static void setParameters(PreparedStatement pstmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }

    // Method to hash the password using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
