package model;
import java.util.ArrayList;
import java.util.List;

public class MusicApp {
    private List<User> users = new ArrayList<>();
    private User loggedInUser = null;

    public void signUp(String username, String password) {
    users.add(new User(username, password));
    System.out.println(username + "님 회원가입 완료!");
}

public boolean login(String username, String password) {
    for (User u : users) {
        if (u.getUsername().equals(username) && u.checkPassword(password)) {
            loggedInUser = u;
            System.out.println(username + "님 로그인 성공!");
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