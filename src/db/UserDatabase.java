package src.db;

import java.sql.*;
import src.model.User;

public class UserDatabase {
    /* --- 실제 DB 접속 정보 (현재 사용 안 함) ---
    private final String url = "jdbc:mysql://localhost:3306/user_db?useSSL=false&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "Skdks2525@";
    */

    // 생성자
    public UserDatabase() {
        /* --- 드라이버 로드 (현재 사용 안 함) ---
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        */
    }

    // 회원가입 (무조건 성공)
    public boolean signUp(String username, String pw, java.sql.Date birthdate, String name, String phone) {
        System.out.println("### DB 연결 비활성화 모드: signUp() 호출됨 ###");
        return true; // 회원가입은 무조건 성공으로 처리

        /* --- 원본 DB 연결 코드 ---
        String sql = "INSERT INTO users (username, password, birthdate, name, phone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, pw);
            pstmt.setDate(3, birthdate);
            pstmt.setString(4, name);
            pstmt.setString(5, phone);

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        */
    }

    // 로그인 (admin/admin123 계정으로만 성공)
    public User login(String username, String pw) {
        System.out.println("### DB 연결 비활성화 모드: login() 호출됨 ###");

        // 아이디와 비밀번호를 모두 확인
        if ("admin".equals(username) && "admin123".equals(pw)) {
            System.out.println("테스트 admin 계정으로 로그인 성공");

            // 테스트용 User 객체 생성 및 반환
            return new User(
                    "admin",
                    "admin123",
                    "관리자", // 이름
                    new java.sql.Date(System.currentTimeMillis()), // 오늘 날짜
                    "010-0000-0000",
                    true // 관리자 계정이므로 isAdmin을 true로 설정
            );
        }

        return null; // 일치하지 않으면 로그인 실패

        /* --- 원본 DB 연결 코드 ---
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

        return null;
        */
    }

    // 사용자 존재 여부 확인 (무조건 존재하지 않음)
    public boolean exists(String username) {
        System.out.println("### DB 연결 비활성화 모드: exists() 호출됨 ###");
        return false; // 회원가입 테스트를 위해 사용자가 없는 것으로 처리

        /* --- 원본 DB 연결 코드 ---
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
        */
    }
}