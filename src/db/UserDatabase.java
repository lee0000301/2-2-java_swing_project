package src.db;

import java.sql.*;
import src.model.User;

public class UserDatabase {
    // DB 접속 정보
    private final String url = "jdbc:mysql://113.198.235.222:10001/JavaSwingDB";
    private final String user = "lee"; 
    private final String password = "ddangsil2@";

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
        String sql = "INSERT INTO user_inf (username, password, birthdate, name, phone, isAdmin) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, pw);
            pstmt.setDate(3, birthdate);
            pstmt.setString(4, name);
            pstmt.setString(5, phone);
            pstmt.setBoolean(6, false); // false = 일반 사용자 계정 생성 | true = 관리자 계정 생성

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 로그인
    public User login(String username, String pw) {
        String sql = "SELECT * FROM user_inf WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, pw);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getDate("birthdate"),
                        rs.getString("phone"),
                        rs.getBoolean("isAdmin") // DB에서 불러오기
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // 로그인 실패
    }

    // 사용자 존재 여부 확인
    public boolean exists(String username) {
        String sql = "SELECT 1 FROM user_inf WHERE username = ?";

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
