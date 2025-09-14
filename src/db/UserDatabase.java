package src.db;

import java.sql.*;

import src.model.User;

public class UserDatabase {
    // DB 접속 정보
    private final String url = "jdbc:mysql://localhost:3306/user_db?useSSL=false&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "uDap1782#5^7";

    // 드라이버 로드
    public UserDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 회원가입
    public boolean signUp(String username, String pw, java.sql.Date birthdate, String name, String phone) {
        String sql = "INSERT INTO users (username, password, birthdate, name, phone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, pw);
            pstmt.setDate(3, birthdate);
            pstmt.setString(4, name);
            pstmt.setString(5, phone);

            int result = pstmt.executeUpdate();
            return result > 0; // 성공하면 true

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 로그인
    public User login(String username, String pw) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, pw);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User u = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getDate("birthdate"),
                        rs.getString("phone"),
                        rs.getBoolean("isAdmin")
                );
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // 로그인 실패
    }

    // 사용자 존재 여부 확인
    public boolean exists(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
