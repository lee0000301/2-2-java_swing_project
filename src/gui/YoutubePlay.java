package src.gui;

import javax.swing.*;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URLEncoder;

public class YoutubePlay extends MouseAdapter {

    private JList<String> list;
    private DefaultListModel<String> model;
    private JFrame parentFrame;
    private JList<String> genreList; // [추가] 장르 리스트를 저장할 변수

    /**
     * 생성자: 장르 리스트(genreList)를 추가로 전달받습니다.
     */
    public YoutubePlay(JList<String> list, DefaultListModel<String> model, JFrame parentFrame, JList<String> genreList) { // [수정]
        this.list = list;
        this.model = model;
        this.parentFrame = parentFrame;
        this.genreList = genreList; // [추가]
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        // [수정] 더블클릭 이벤트를 처리하기 전에, '내 재생목록'이 선택되었는지 먼저 확인합니다.
        if (evt.getClickCount() == 2 && "내 재생목록".equals(genreList.getSelectedValue())) {
            int index = list.locationToIndex(evt.getPoint());
            if (index < 0) return;

            String selectedItem = model.getElementAt(index);

            if (selectedItem != null && selectedItem.contains(" - ")) {
                try {
                    String encodedSearchTerm = URLEncoder.encode(selectedItem, "UTF-8");
                    String youtubeUrl = "https://www.youtube.com/results?search_query=" + encodedSearchTerm;
                    Desktop.getDesktop().browse(new URI(youtubeUrl));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(parentFrame, "링크를 여는 데 실패했습니다.");
                    ex.printStackTrace();
                }
            }
        }
    }
}