package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.User;
import model.Song;
import java.util.List;

public class PlaylistFrame extends JFrame {
    private User user;
    private DefaultListModel<String> listModel;
    private JList<String> playlistList;
    private JTextField titleField, artistField;

    public PlaylistFrame(User user) {
        this.user = user;
        setTitle(user.getUsername() + "님의 플레이리스트");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 리스트 모델 및 JList
        listModel = new DefaultListModel<>();
        playlistList = new JList<>(listModel);
        refreshPlaylist();

        JScrollPane scrollPane = new JScrollPane(playlistList);

        // 입력 필드 패널 (두 줄로 분리)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.add(new JLabel("제목:"));
        titleField = new JTextField(10);
        fieldPanel.add(titleField);
        fieldPanel.add(new JLabel("가수:"));
        artistField = new JTextField(10);
        fieldPanel.add(artistField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addBtn = new JButton("노래 추가");
        JButton removeBtn = new JButton("노래 삭제");
        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);

        inputPanel.add(fieldPanel);
        inputPanel.add(buttonPanel);

        addBtn.addActionListener(e -> {
            String title = titleField.getText().trim();
            String artist = artistField.getText().trim();
            if (!title.isEmpty() && !artist.isEmpty()) {
                user.addSong(title, artist);
                refreshPlaylist();
                titleField.setText("");
                artistField.setText("");
            }
        });

        removeBtn.addActionListener(e -> {
            int idx = playlistList.getSelectedIndex();
            if (idx != -1) {
                String selected = listModel.get(idx);
                String title = selected.split(" - ")[0].replace("\"", "");
                user.removeSong(title);
                refreshPlaylist();
            }
        });

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void refreshPlaylist() {
        listModel.clear();
        List<Song> playlist = user.getPlaylist();
        for (Song song : playlist) {
            listModel.addElement(song.toString());
        }
    }
}