package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.MusicApp;
import model.User;

// 로그인, 회원가입 할 수 있는 GUI 창 

public class LoginFrame extends JFrame {
    private MusicApp app;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame(MusicApp app) {
        this.app = app;
        setTitle("로그인 / 회원가입");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        panel.add(new JLabel("아이디:"));
        usernameField = new JTextField(15);
        panel.add(usernameField);

        panel.add(new JLabel("비밀번호:"));
        passwordField = new JPasswordField(15);
        panel.add(passwordField);

        JButton loginBtn = new JButton("로그인");
        JButton signupBtn = new JButton("회원가입");
        panel.add(loginBtn);
        panel.add(signupBtn);

        add(panel, BorderLayout.CENTER);

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            if (app.login(username, password)) {
                User user = app.getLoggedInUser();
                JOptionPane.showMessageDialog(this, "로그인 성공!");
                dispose();
                new PlaylistFrame(user, app);
            } else {
                JOptionPane.showMessageDialog(this, "로그인 실패: 아이디 또는 비밀번호를 확인하세요.");
            }
        });

        signupBtn.addActionListener(e -> {
            new signupFrame(app);
        });
            

        setVisible(true);
    }
}