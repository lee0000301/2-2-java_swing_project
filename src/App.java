package src;
import src.gui.LoginFrame;
import src.model.MusicApp;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        MusicApp app = new MusicApp();

        SwingUtilities.invokeLater(() -> {
            JFrame menuFrame = new JFrame("MUSIC APP");
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuFrame.setSize(400, 250);
            menuFrame.setLocationRelativeTo(null);

            // 상단 타이틀 및 아이콘
            JLabel titleLabel = new JLabel("MUSIC APP", SwingConstants.CENTER);
            titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
            titleLabel.setIcon(UIManager.getIcon("FileView.audioIcon"));

            // 로그인/회원가입 버튼
            JButton loginBtn = new JButton("로그인 / 회원가입");
            loginBtn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
            loginBtn.setBackground(new Color(100, 180, 255));
            loginBtn.setForeground(Color.WHITE);
            loginBtn.setFocusPainted(false);
            loginBtn.setPreferredSize(new Dimension(220, 50));
            loginBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            loginBtn.addActionListener(e -> new LoginFrame(app));

            // 레이아웃 구성
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(240, 248, 255));
            mainPanel.add(titleLabel, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel();
            centerPanel.setBackground(new Color(240, 248, 255));
            centerPanel.add(loginBtn);

            mainPanel.add(centerPanel, BorderLayout.CENTER);

            menuFrame.setContentPane(mainPanel);
            menuFrame.setVisible(true);
        });
    }
}