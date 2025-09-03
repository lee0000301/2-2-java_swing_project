package model;

import java.util.ArrayList;
import java.util.List;

//사용자 클래스

public class User {
    private String username;
    private String password;
    private List<Song> playlist; // 사용자별 플레이리스트

    public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.playlist = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    //플레이리스트 기능
    public void addSong(String title, String artist) {
        Song song = new Song(title, artist);
        playlist.add(song);
        System.out.println(song + "이(가) 플레이리스트에 추가되었습니다.");
    }

    public void removeSong(String title) {
        for (Song song : playlist) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                playlist.remove(song);
                System.out.println(song + "이(가) 플레이리스트에서 삭제되었습니다.");
                return;
            }
        }
        System.out.println(title + "은(는) 플레이리스트에 없습니다.");
    }

    public void showPlaylist() {
        System.out.println("\n[" + username + "님의 플레이리스트]");
        if (playlist.isEmpty()) {
            System.out.println("플레이리스트가 비어있습니다.");
        } else {
            for (int i = 0; i < playlist.size(); i++) {
                System.out.println((i + 1) + ". " + playlist.get(i));
            }
        }
    }

    public List<Song> getPlaylist() {
        return playlist;
    }

}