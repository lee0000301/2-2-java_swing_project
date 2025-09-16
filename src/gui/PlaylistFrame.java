package src.gui;

import javax.swing.*;
<<<<<<< HEAD
import java.util.Map;
=======

>>>>>>> c6c6f13 (password delete)
import src.model.MusicApp;
import src.model.Song;
import src.model.User;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
<<<<<<< HEAD
// java.sql.*;

// 로그인한 사용자를 위한 주요 음악 앱 GUI, 장르 목록, 사용자 재생목록 관리
=======
import java.sql.*;

// 로그인한 사용자를 위한 주요 음악 앱 GUI, 장르 목록, 사용자 재생목록 관리

>>>>>>> c6c6f13 (password delete)
public class PlaylistFrame extends JFrame {
    private User user;
    private MusicApp app;
    private DefaultListModel<String> listModel;
    private JList<String> playlistList;
<<<<<<< HEAD
    private JTextField titleField, artistField, searchField;
    private JComboBox<String> categoryComboBox;
    private JPanel searchPanel;
    private JButton searchButton;
    private JRadioButton titleRadioButton, artistRadioButton;
    private ButtonGroup searchTypeGroup;
    private JButton createPlaylistButton;
=======
    private JTextField titleField, artistField; 
    private JComboBox<String> categoryComboBox; 
>>>>>>> c6c6f13 (password delete)

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

<<<<<<< HEAD
        // 왼쪽 : 장르 선택 패널
        String[] genres = {"검색", "내 재생목록", "TOP 100", "발라드", "댄스", "힙합", "POP"};
=======
        // 왼쪽 : 장르 선택 패널 
        String[] genres = {"내 재생목록", "TOP 100", "발라드", "댄스", "힙합", "POP"};
>>>>>>> c6c6f13 (password delete)
        JList<String> genreList = new JList<>(genres);
        genreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        genreList.setBorder(BorderFactory.createTitledBorder("장르 선택"));
        genreList.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        genreList.setFixedCellHeight(35);

        genreList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedGenre = genreList.getSelectedValue();
                listModel.clear(); // 기존 목록 비우기
<<<<<<< HEAD

                if ("검색".equals(selectedGenre)) {
                    searchPanel.setVisible(true); // 검색 패널 보이기
                    listModel.addElement("상단 검색창을 이용해 노래를 검색하세요.");
                } else {
                    searchPanel.setVisible(false); // 검색 패널 숨기기

                    if ("내 재생목록".equals(selectedGenre)) {
                        refreshPlaylist();
                    } else {
                        List<String> songs = src.model.Search.getSongsByGenre(selectedGenre);
                        for (String song : songs) {
                            listModel.addElement(song);
                        }
=======
                
                if ("내 재생목록".equals(selectedGenre)) {
                    refreshPlaylist();
                } else {
                    List<String> songs = src.model.Search.getSongsByGenre(selectedGenre);
                    for (String song : songs) {
                        listModel.addElement(song);
>>>>>>> c6c6f13 (password delete)
                    }
                }
            }
        });

        JPanel genrePanel = new JPanel(new BorderLayout());
        genrePanel.add(new JScrollPane(genreList), BorderLayout.CENTER);

        // 오른쪽: 내 재생목록 패널
        JPanel playlistPanel = new JPanel(new BorderLayout());

<<<<<<< HEAD
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        searchButton = new JButton("검색");

        titleRadioButton = new JRadioButton("제목");
        artistRadioButton = new JRadioButton("가수");
        titleRadioButton.setSelected(true); // default : 제목

        searchTypeGroup = new ButtonGroup();
        searchTypeGroup.add(titleRadioButton);
        searchTypeGroup.add(artistRadioButton);

        searchPanel.add(new JLabel("검색: "));
        searchPanel.add(titleRadioButton);
        searchPanel.add(artistRadioButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.setVisible(false);

        playlistPanel.add(searchPanel, BorderLayout.NORTH);

        // [수정] 검색 버튼 이벤트 리스너
        searchButton.addActionListener(e -> {
            String query = searchField.getText(); // 기존 변수명 searchField 사용
            String searchType = titleRadioButton.isSelected() ? "title" : "artist";

            listModel.clear(); // 기존 변수명 listModel 사용

            // Search.java로부터 Map 형태로 결과를 받음 (새 지역 변수 searchResult)
            Map<String, Object> searchResult = src.model.Search.searchSongs(query, searchType);

            if (searchResult.isEmpty()) {
                listModel.addElement("'" + query + "'에 대한 검색 결과가 없습니다.");
                return;
            }

            // 제목으로 검색한 경우
            if ("title".equals(searchType)) {
                // Map에서 'mainResult' 키로 메인 결과를 꺼냄 (새 지역 변수 mainResult)
                String mainResult = (String) searchResult.get("mainResult");
                // Map에서 'otherSongs' 키로 다른 노래 목록을 꺼냄 (새 지역 변수 otherSongs)
                List<String> otherSongs = (List<String>) searchResult.get("otherSongs");

                if (mainResult != null) {
                    listModel.addElement(mainResult); // 1. 최상단에 메인 결과 표시
                }

                if (otherSongs != null && !otherSongs.isEmpty()) {
                    listModel.addElement("────────── 이 아티스트의 다른 곡 ──────────"); // 2. 구분선 추가
                    for (String song : otherSongs) {
                        listModel.addElement(song); // 3. 다른 곡 목록 표시
                    }
                }
            }
            // 가수로 검색한 경우
            else {
                List<String> artistSongs = (List<String>) searchResult.get("otherSongs");
                if (artistSongs != null && !artistSongs.isEmpty()) {
                    for (String song : artistSongs) {
                        listModel.addElement(song);
                    }
                }
            }
        });

        listModel = new DefaultListModel<>();
        playlistList = new JList<>(listModel);
        playlistList.addMouseListener(new YoutubePlay(playlistList, listModel, this, genreList));
=======
        listModel = new DefaultListModel<>();
        playlistList = new JList<>(listModel);
>>>>>>> c6c6f13 (password delete)
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
<<<<<<< HEAD


        if (!user.isAdmin()) {

            // '재생목록 담기' 버튼 추가
            JButton addToMyPlaylistBtn = new JButton("재생목록 담기");
            addToMyPlaylistBtn.addActionListener(e -> {
                String selectedSong = playlistList.getSelectedValue();
                if (selectedSong != null && selectedSong.contains(" - ")) {
                    String[] parts = selectedSong.split(" - ");
=======
        

        if (!user.isAdmin()) {
            
        // '재생목록 담기' 버튼 추가
        JButton addToMyPlaylistBtn = new JButton("재생목록 담기");
        addToMyPlaylistBtn.addActionListener(e -> {
            String selectedSong = playlistList.getSelectedValue();
            if (selectedSong != null) {
                String[] parts = selectedSong.split(" - ");
                if (parts.length >= 2) {
>>>>>>> c6c6f13 (password delete)
                    String artist = parts[0].trim();
                    String title = parts[1].trim();
                    user.addSong(new Song(title, artist));
                    JOptionPane.showMessageDialog(this, "'" + title + "'이(가) 내 재생목록에 추가되었습니다.");
<<<<<<< HEAD
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
                    if (selected.contains(" - ")) {
                        // [버그 수정] "가수 - 제목" 형식에서 제목은 parts[1] 입니다.
                        String title = selected.split(" - ")[1].trim();
                        user.removeSong(title);
                        refreshPlaylist();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "먼저 삭제할 노래를 선택하세요.");
                }
            });

            JPanel commonButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            commonButtonsPanel.add(addToMyPlaylistBtn);
            commonButtonsPanel.add(removeFromMyPlaylistBtn);
            controlPanel.add(commonButtonsPanel);
=======
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
>>>>>>> c6c6f13 (password delete)

        }

        // 관리자용 UI 및 기능
        if (user.isAdmin()) {
            JPanel songInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            titleField = new JTextField(10);
            artistField = new JTextField(10);

            // 카테고리 선택 콤보박스
            String[] categories = {"TOP 100", "발라드", "댄스", "힙합", "POP"};
            categoryComboBox = new JComboBox<>(categories);
<<<<<<< HEAD

=======
            
>>>>>>> c6c6f13 (password delete)
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
<<<<<<< HEAD
                    if(selected.contains(" - ")) {
                        // [버그 수정] "가수 - 제목" 형식에서 제목은 parts[1] 입니다.
                        String title = selected.split(" - ")[1].trim();
                        user.removeSong(title);
                        refreshPlaylist();
                    }
                }
            });
        }

=======
                    String title = selected.split(" - ")[0].trim();
                    user.removeSong(title);
                    refreshPlaylist();
                }
            });
        }
        
>>>>>>> c6c6f13 (password delete)
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