package model;
import java.util.ArrayList;
import java.util.List;

// 사용자 계정 관리, 로그인 및 회원가입 처리 수행

public class MusicApp {
    private List<User> users = new ArrayList<>();
    private User loggedInUser = null;

    public MusicApp() {
        // 관리자 계정 고정 생성 (아이디: admin, 비밀번호: admin123)
        users.add(new User("admin", "admin123", true));
    }

    public void signUp(String username, String password) {
        users.add(new User(username, password, false)); // 일반 사용자
        System.out.println(username + "님 회원가입 완료!");
    }

    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.checkPassword(password)) {
                loggedInUser = u;
                return true;
            }
        }
        System.out.println("로그인 실패: 아이디 또는 비밀번호를 확인하세요.");
        return false;
    }

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