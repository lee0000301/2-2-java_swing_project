import model.Search;
import java.util.*;

public class SongListDemo {
    public static void main(String[] args) {
        Map<String, List<String>> artistMap = Search.getArtistMap();
        for (String artist : artistMap.keySet()) {
            System.out.println("[" + artist + "]");
            for (String song : artistMap.get(artist)) {
                System.out.println(" - " + song);
            }
        }
    }
}