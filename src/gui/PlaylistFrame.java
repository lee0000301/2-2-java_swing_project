package src.gui;

import javax.swing.*;

import src.model.MusicApp;
import src.model.Song;
import src.model.User;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.sql.*;

// 로그인한 사용자를 위한 주요 음악 앱 GUI, 장르 목록, 사용자 재생목록 관리

public class PlaylistFrame extends JFrame {
    private User user;
    private MusicApp app;
    private DefaultListModel<String> listModel;
    private JList<String> playlistList;
    private JTextField titleField, artistField; 
    private JComboBox<String> categoryComboBox; 

    public PlaylistFrame(User user, MusicApp app) {
        this.user = user;
        this.app = app;
        setTitle(user.getUsername() + "님의 음악 앱");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 상단 타이틀
        JLabel titleLabel = new JLabel("MUSIC APP", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(titleLabel, BorderLayout.NORTH);

        // 왼쪽 : 장르 선택 패널 
        String[] genres = {"내 재생목록", "TOP 100", "발라드", "댄스", "힙합", "POP"};
        JList<String> genreList = new JList<>(genres);
        genreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        genreList.setBorder(BorderFactory.createTitledBorder("장르 선택"));
        genreList.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        genreList.setFixedCellHeight(35);

        genreList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedGenre = genreList.getSelectedValue();
                listModel.clear(); // 기존 목록 비우기
                
                if ("내 재생목록".equals(selectedGenre)) {
                    refreshPlaylist();
                } else {
                    List<String> songs = src.model.Search.getSongsByGenre(selectedGenre);
                    for (String song : songs) {
                        listModel.addElement(song);
                    }
                }
            }
        });

        JPanel genrePanel = new JPanel(new BorderLayout());
        genrePanel.add(new JScrollPane(genreList), BorderLayout.CENTER);

        // 오른쪽: 내 재생목록 패널
        JPanel playlistPanel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel<>();
        playlistList = new JList<>(listModel);
        playlistList.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        playlistList.setFixedCellHeight(40);
        playlistList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        playlistList.setSelectionBackground(new Color(220, 240, 255));
        playlistList.setSelectionForeground(Color.BLACK);

        playlistList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
                label.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
                label.setOpaque(true);
                if (isSelected) {
                    label.setBackground(new Color(200, 220, 255));
                } else {
                    label.setBackground(Color.WHITE);
                }
                label.setIcon(UIManager.getIcon("FileView.audioIcon"));
                return label;
            }
        });

        refreshPlaylist(); // 초기 플레이리스트 로드

        JScrollPane scrollPane = new JScrollPane(playlistList);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        

        if (!user.isAdmin()) {
            
        // '재생목록 담기' 버튼 추가
        JButton addToMyPlaylistBtn = new JButton("재생목록 담기");
        addToMyPlaylistBtn.addActionListener(e -> {
            String selectedSong = playlistList.getSelectedValue();
            if (selectedSong != null) {
                String[] parts = selectedSong.split(" - ");
                if (parts.length >= 2) {
                    String artist = parts[0].trim();
                    String title = parts[1].trim();
                    user.addSong(new Song(title, artist));
                    JOptionPane.showMessageDialog(this, "'" + title + "'이(가) 내 재생목록에 추가되었습니다.");
                    refreshPlaylist();
                }
            } else {
                JOptionPane.showMessageDialog(this, "먼저 노래를 선택하세요.");
            }
        });

        // '재생 목록 삭제' 버튼 추가
        JButton removeFromMyPlaylistBtn = new JButton("재생목록 삭제");
        removeFromMyPlaylistBtn.addActionListener(e -> {
            int idx = playlistList.getSelectedIndex();
            if (idx != -1) {
                String selected = listModel.get(idx);
                String title = selected.split(" - ")[0].trim();
                user.removeSong(title);
                refreshPlaylist();
            } else {
                JOptionPane.showMessageDialog(this, "먼저 삭제할 노래를 선택하세요.");
            }
        });
        
        JPanel commonButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        commonButtonsPanel.add(addToMyPlaylistBtn);
        commonButtonsPanel.add(removeFromMyPlaylistBtn);
        controlPanel.add(commonButtonsPanel);

        }

        // 관리자용 UI 및 기능
        if (user.isAdmin()) {
            JPanel songInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            titleField = new JTextField(10);
            artistField = new JTextField(10);

            // 카테고리 선택 콤보박스
            String[] categories = {"TOP 100", "발라드", "댄스", "힙합", "POP"};
            categoryComboBox = new JComboBox<>(categories);
            
            songInputPanel.add(new JLabel("카테고리:"));
            songInputPanel.add(categoryComboBox);
            songInputPanel.add(new JLabel("제목:"));
            songInputPanel.add(titleField);
            songInputPanel.add(new JLabel("가수:"));
            songInputPanel.add(artistField);

            JPanel songButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton addSongBtn = new JButton("노래 추가");
            JButton removeSongBtn = new JButton("노래 삭제");
            addSongBtn.setBackground(new Color(100, 180, 255));
            addSongBtn.setForeground(Color.WHITE);
            removeSongBtn.setBackground(new Color(255, 120, 120));
            removeSongBtn.setForeground(Color.WHITE);
            songButtons.add(addSongBtn);
            songButtons.add(removeSongBtn);

            controlPanel.add(songInputPanel);
            controlPanel.add(songButtons);

            // 관리자 전용 노래 추가/삭제 이벤트 리스너
            addSongBtn.addActionListener(e -> {
                String title = titleField.getText().trim();
                String artist = artistField.getText().trim();
                if (!title.isEmpty() && !artist.isEmpty()) {
                    user.addSong(new Song(title, artist));
                    refreshPlaylist();
                    titleField.setText("");
                    artistField.setText("");
                }
            });

            removeSongBtn.addActionListener(e -> {
                int idx = playlistList.getSelectedIndex();
                if (idx != -1) {
                    String selected = listModel.get(idx);
                    String title = selected.split(" - ")[0].trim();
                    user.removeSong(title);
                    refreshPlaylist();
                }
            });
        }
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, genrePanel, playlistPanel);
        splitPane.setDividerLocation(200);

        playlistPanel.add(scrollPane, BorderLayout.CENTER);
        playlistPanel.add(controlPanel, BorderLayout.SOUTH);
        add(splitPane, BorderLayout.CENTER);

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