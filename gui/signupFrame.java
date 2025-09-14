package gui;

import javax.swing.*;
import java.awt.*;
import model.MusicApp;

// 회원가입 정보 입력하는 GUI 창

public class signupFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField birthdateField;
    private JTextField phoneField;

    public signupFrame(MusicApp app) {
        setTitle("회원가입");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        panel.add(new JLabel("생년월일 (YYYYMMDD):"));
        birthdateField= new JTextField(15);
        panel.add(birthdateField);

        panel.add(new JLabel("휴대폰 번호 : "));
        phoneField = new JTextField(15);
        panel.add(phoneField);
        
        panel.add(new JLabel("아이디:"));
        usernameField = new JTextField(15);
        panel.add(usernameField);

        panel.add(new JLabel("비밀번호:"));
        passwordField = new JPasswordField(15);
        panel.add(passwordField);

        JButton signupBtn = new JButton("회원가입");
        panel.add(new JLabel()); // 빈 칸
        panel.add(signupBtn);

        add(panel, BorderLayout.CENTER);

        signupBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String birthdate = birthdateField.getText().trim();
            String phone = phoneField.getText().trim();

            // 생년월일 8자리 검사
            if (!birthdate.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(this, "생년월일은 YYYYMMDD 8자리 숫자로 입력하세요.");
                return;
            }
            // 생년월일 유효 여부 검사
            int year = Integer.parseInt(birthdate.substring(0, 4));
            int month = Integer.parseInt(birthdate.substring(4, 6));
            int day = Integer.parseInt(birthdate.substring(6, 8));
            if (year < 1900 || year > 2025) {
                JOptionPane.showMessageDialog(this, "생년월일의 연도는 1900년 이상, 2025년 이하로 입력하세요.");
                return;
            }
            if (month < 1 || month > 12) {
                JOptionPane.showMessageDialog(this, "생년월일의 월은 1월~12월 사이여야 합니다.");
                return;
            }
            if (day < 1 || day > 31) {
                JOptionPane.showMessageDialog(this, "생년월일의 일은 1일~31일 사이여야 합니다.");
                return;
            }

            // 전화번호 형식 검사 (000-0000-0000)
            if (!phone.matches("\\d{3}-\\d{4}-\\d{4}")) {
                JOptionPane.showMessageDialog(this, "전화번호는 000-0000-0000 형식으로 입력하세요.");
                return;
            }

                    // 아이디/비밀번호 공백 및 길이 검사
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 모두 입력하세요.");
                return;
            }
            if (username.length() < 5 || username.length() > 15) {
                JOptionPane.showMessageDialog(this, "아이디는 5자 이상 15자 이하로 입력하세요.");
                return;
            }
            if (password.length() < 8 || password.length() > 20) {
                JOptionPane.showMessageDialog(this, "비밀번호는 8자 이상 20자 이하로 입력하세요.");
                return;
            }

            app.signUp(username, password);
            JOptionPane.showMessageDialog(this, "회원가입 완료! 이제 로그인하세요.");
            dispose(); // 회원가입 창 닫기
        });

        setVisible(true);
    }
}