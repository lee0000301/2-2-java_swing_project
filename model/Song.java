package model;

//노래 클래스: 제목 + 가수 정보 저장
public class Song {
    private String title; // 노래 제목
    private String artist; // 가수 이름
    
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return title + " - " + artist; // 출력 시 "제목 - 가수" 형태
    }
}