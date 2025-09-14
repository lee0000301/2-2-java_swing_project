package src.model;

import src.db.UserDatabase;
import src.model.User;

// 사용자 계정 관리, 로그인 및 회원가입 처리 (DB 연동)
public class MusicApp {
    private User loggedInUser = null;
    private final UserDatabase userDB = new UserDatabase();

    public MusicApp() {
        // 초기 관리자 계정은 DB에서 직접 관리
        // MusicApp에서는 관리자 여부 처리하지 않음
    }

    // 회원가입
    public boolean signUp(String username, String password, java.sql.Date birthdate, String name, String phone) {
        boolean success = userDB.signUp(username, password, birthdate, name, phone); // isAdmin은 DB에서 기본값 false
        if (success) {
            System.out.println(username + "님 회원가입 완료!");
        }
        return success;
    }

    // 로그인
    public boolean login(String username, String password) {
        User user = userDB.login(username, password); // User 객체 내부에 isAdmin 포함 가능
        if (user != null) {
            loggedInUser = user;
            System.out.println(username + "님 로그인 성공!");
            return true;
        }
        System.out.println("로그인 실패: 아이디 또는 비밀번호를 확인하세요.");
        return false;
    }

    // 로그아웃
    public void logout() {
        if (loggedInUser != null) {
            System.out.println(loggedInUser.getUsername() + "님 로그아웃되었습니다.");
            loggedInUser = null;
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
