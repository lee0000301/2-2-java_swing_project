package model;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MusicApp app = new MusicApp();

            while (true) {
        System.out.println("\n=== 메인 메뉴 ===");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("3. 종료");
        System.out.print("선택: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.print("아이디 입력: ");
            String username = sc.nextLine();
            System.out.print("비밀번호 입력: ");
            String password = sc.nextLine();
            app.signUp(username, password);

        } else if (choice == 2) {
            System.out.print("아이디 입력: ");
            String username = sc.nextLine();
            System.out.print("비밀번호 입력: ");
            String password = sc.nextLine();

            if (app.login(username, password)) {
                User currentUser = app.getLoggedInUser();
                while (true) {
                    System.out.println("\n=== 플레이리스트 메뉴 ===");
                    System.out.println("1. 노래 추가");
                    System.out.println("2. 노래 삭제");
                    System.out.println("3. 플레이리스트 보기");
                    System.out.println("4. 로그아웃");
                    System.out.print("선택: ");
                    int playlistChoice = sc.nextInt();
                    sc.nextLine();

                    if (playlistChoice == 1) {
                        System.out.print("노래 제목 입력: ");
                        String title = sc.nextLine();
                        System.out.print("가수 입력: ");
                        String artist = sc.nextLine();
                        currentUser.addSong(title, artist);
                    } else if (playlistChoice == 2) {
                        System.out.print("삭제할 노래 제목 입력: ");
                        String title = sc.nextLine();
                        currentUser.removeSong(title);
                    } else if (playlistChoice == 3) {
                        currentUser.showPlaylist();
                    } else if (playlistChoice == 4) {
                        app.logout();
                        break;
                    } else {
                        System.out.println("잘못된 입력입니다.");
                    }
                }
            }

        } else if (choice == 3) {
            System.out.println("프로그램 종료.");
            break;
        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }

    sc.close();
    }
}
