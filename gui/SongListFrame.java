package gui;
// 예시: PlaylistFrame이나 별도 프레임에서 전체 노래 리스트 보여주기
import model.Search;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SongListFrame extends JFrame {
    public SongListFrame() {
        setTitle("전체 노래 리스트");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultListModel<String> model = new DefaultListModel<>();
        Map<String, java.util.List<String>> artistMap = Search.getArtistMap();
        for (String artist : artistMap.keySet()) {
            for (String song : artistMap.get(artist)) {
                model.addElement(artist + " - " + song);
            }
        }

        JList<String> songList = new JList<>(model);
        add(new JScrollPane(songList), BorderLayout.CENTER);

        setVisible(true);
    }
}