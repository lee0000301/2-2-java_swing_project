import gui.LoginFrame;
import gui.SongListFrame;
import model.MusicApp;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        MusicApp app = new MusicApp();

        SwingUtilities.invokeLater(() -> {
            JFrame menuFrame = new JFrame("음악 앱 메인 메뉴");
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuFrame.setSize(300, 150);
            menuFrame.setLocationRelativeTo(null);

            JButton loginBtn = new JButton("로그인/회원가입");
            JButton songListBtn = new JButton("전체 노래 리스트 보기");

            loginBtn.addActionListener(e -> new LoginFrame(app));
            songListBtn.addActionListener(e -> new SongListFrame());

            JPanel panel = new JPanel();
            panel.add(loginBtn);
            panel.add(songListBtn);

            menuFrame.add(panel);
            menuFrame.setVisible(true);
        });
    }
}