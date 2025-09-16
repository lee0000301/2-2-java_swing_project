package src.model;
import java.util.*;
<<<<<<< HEAD
import java.util.stream.Collectors;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    // 모든 노래 데이터를 담는 마스터 리스트 (가수별로 관리)
    private static final Map<String, List<String>> artistMap = new HashMap<>();

    // 노래 제목으로 가수를 찾기 위한 역방향 맵
    private static final Map<String, String> songToArtistMap = new HashMap<>();

    public static List<String> getSongsByGenre(String genre) {
=======

public class Search {

    // 가수별 노래 리스트 저장
    private static Map<String, List<String>> artistMap = new HashMap<>();

    // static 블록에서 노래 데이터 추가
    static {
        // 노래제목, 가수 딕셔너리
        addSong(artistMap, "헌트릭스(Huntrix)", "Golden");
        addSong(artistMap, "사자 보이즈(Saja Boys)", "Soda Pop");
        addSong(artistMap, "블랙핑크(Blackpink)", "뛰어");
        addSong(artistMap, "올데이 프로젝트(Allday Project)", "Famous");
        addSong(artistMap, "우즈(WOODZ)", "Drowing");
        addSong(artistMap, "에스파(Aespa)", "Dirty Work");
        addSong(artistMap, "마크툽(Maktub)", "시작의 아이");
        addSong(artistMap, "10cm", "너에게 닿기를");
        addSong(artistMap, "사자 보이즈(Saja Boys)", "Your Idol");
        addSong(artistMap, "조째즈", "모르시나요");
        addSong(artistMap, "올데이 프로젝트(Allday Project)", "Wicked");
        addSong(artistMap, "우디(Woody)", "어제보다 슬픈 오늘");
        addSong(artistMap, "에스파(Aespa)", "Whiplash");
        addSong(artistMap, "제니(Jennie)", "Like Jennie");
        addSong(artistMap, "지드래곤 ft 태양, 대성", "Home Sweet Home");
        addSong(artistMap, "이무진", "청춘만화");
        addSong(artistMap, "아이유(IU)", "Never Ending Story");
        addSong(artistMap, "헌트릭스(Huntrix)", "How It's Done");
        addSong(artistMap, "황가람", "나는 반딧불");
        addSong(artistMap, "로제(ROSÉ) & Bruno Mars", "APT.");
        addSong(artistMap, "지드래곤(G-dragon) ft Anderson Pakk", "Too Bad");
        addSong(artistMap, "데이식스(Day6)", "Happy");
        addSong(artistMap, "아이브(IVE)", "Rebel Heart");
        addSong(artistMap, "보이넥스트도어(BoyNextDoor)", "오늘만 I Love You");
        addSong(artistMap, "미야오(Meovv)", "Hands Up");
        addSong(artistMap, "QWER", "눈물참기");
        addSong(artistMap, "아일릿(Illit)", "빌려온 고양이");
        addSong(artistMap, "데이식스(Day6)", "한 페이지가 될 수 있게");
        addSong(artistMap, "하츠투하츠(Hearts2Hearts)", "Style");
        addSong(artistMap, "이창섭", "천상연");
        addSong(artistMap, "프로미스나인(Promis9)", "Like You Better");
        addSong(artistMap, "이클립스(Eclipse)", "소나기");
        addSong(artistMap, "에스파(Aespa)", "Supernova");
        addSong(artistMap, "로이킴(Roykim)", "내게 사랑이 뭐냐고 물어본다면");
        addSong(artistMap, "로제(ROSÉ)", "Toxic Till The End");
        addSong(artistMap, "오반", "Flower");
        addSong(artistMap, "데이식스(Day6)", "Welcome To The Show");
        addSong(artistMap, "이예은, 아샤트리, 전건호", "My Love");
        addSong(artistMap, "Bruno Mars & Lady Gaga", "Die With A Smile");
        addSong(artistMap, "데이식스(Day6)", "예뻤어");
        addSong(artistMap, "먼데이 키즈 & 이이경", "운명(2025)");
        addSong(artistMap, "악뮤(Akmu)", "어떻게 이별까지 사랑하겠어");
        addSong(artistMap, "TWS", "첫 만남은 계획대로 되지 않아");
        addSong(artistMap, "하이키(H1-Key)", "여름이었다");
        addSong(artistMap, "QWER", "고민중독");
        addSong(artistMap, "QWER", "내 이름 맑음");
        addSong(artistMap, "피프티 피프티(Fifty Fifty)", "Pookie");
        addSong(artistMap, "르세라핌(Le Sserafim)", "Hot");
        addSong(artistMap, "너드커넥션(Nerd Connection)", "그대만 있다면");
        addSong(artistMap, "베이비몬스터(BabyMonster)", "Drip");
        addSong(artistMap, "헌트릭스(Huntrix)", "Free");
        addSong(artistMap, "뉴진스(NewJeans)", "Hype Boy");
        addSong(artistMap, "아이브(IVE)", "I Am");
        addSong(artistMap, "아이유(IU)", "네모의 꿈");
        addSong(artistMap, "트와이스(Twice)", "Takedown");
        addSong(artistMap, "뉴진스(NewJeans)", "How Sweet");
        addSong(artistMap, "카리나(Karina)", "Up");
        addSong(artistMap, "아이브(IVE)", "Attitude");
        addSong(artistMap, "프로미스나인(Fromis_9)", "Supersonic");
        addSong(artistMap, "아일릿(ILLIT)", "Magnetic");
        addSong(artistMap, "투모로우바이투게더", "그날이 오면");
        addSong(artistMap, "이무진", "청혼하지 않을 이유를 못 찾았어");
        addSong(artistMap, "아이유(IU)", "Love Wins All");
        addSong(artistMap, "잔나비", "주저하는 연인들을 위해");
        addSong(artistMap, "이무진", "에피소드");
        addSong(artistMap, "순순희", "슬픈 초대장");
        addSong(artistMap, "지드래곤(G-Dragon)", "Power");
        addSong(artistMap, "키키(Kii Kii)", "I Do Me");
        addSong(artistMap, "성시경", "너의 모든 순간");
        addSong(artistMap, "임재현", "비의 랩소디");
        addSong(artistMap, "멜로망스", "사랑인가 봐");
        addSong(artistMap, "여자아이들(G-I-dle)", "나는 아픈 건 딱 질색이니까");
        addSong(artistMap, "키스 오브 라이프(Kiss Of Life)", "Sticky");
        addSong(artistMap, "황가람", "미치게 그리워서");
        addSong(artistMap, "폴킴", "모든 날, 모든 순간");
        addSong(artistMap, "경서예지 & 전건호", "다정히 내 이름을 부르면");
        addSong(artistMap, "정국 ft Latto", "Seven");
        addSong(artistMap, "세븐틴(Seventeen)", "Thunder");
        addSong(artistMap, "박재정", "헤어지자 말해요");
        addSong(artistMap, "임영웅", "사랑은 늘 도망가");
        addSong(artistMap, "범진", "인사");
        addSong(artistMap, "이영지 ft 도경수", "Small Girl");
        addSong(artistMap, "우디(Woody)", "사막에서 꽃을 피우듯");
        addSong(artistMap, "보이넥스트도어(BoyNextDoor)", "-78");
        addSong(artistMap, "지드래곤(G-dragon)", "무제");
        addSong(artistMap, "헌트릭스(Huntrix)", "What It Sounds Like");
        addSong(artistMap, "하츠투하츠(Hearts2Hearts)", "The Chase");
        addSong(artistMap, "헌트릭스(Huntrix)", "Takedown");
        addSong(artistMap, "방탄소년단(BTS)", "봄날");
        addSong(artistMap, "도경수", "영원해");
        addSong(artistMap, "지드래곤(G-dragon)", "Take Me");
        addSong(artistMap, "라이즈(Riize)", "Fly Up");
        addSong(artistMap, "김민석", "취중고백");
        addSong(artistMap, "에스파(Aespa)", "Armageddon");
        addSong(artistMap, "임영웅", "우리들의 블루스");
        addSong(artistMap, "뉴진스(NewJeans)", "Ditto");
        addSong(artistMap, "플레이브(Plave)", "Dash");
        addSong(artistMap, "제니(Jennie)", "Mantara");
        addSong(artistMap, "임영웅", "다시 만날 수 있을까");
        addSong(artistMap, "이찬혁", "멸종위기사랑");
    }

    public static List<String> getSongsByGenre(String genre) {
    
>>>>>>> c6c6f13 (password delete)
    Map<String, List<String>> genreMap = new HashMap<>();
    genreMap.put("인기차트", Arrays.asList("뉴진스(NewJeans) - Ditto",
                                                    "뉴진스(NewJeans) - Hype Boy",
                                                    "뉴진스(NewJeans) - How Sweet",
                                                    "에스파(Aespa) - Supernova",
                                                    "에스파(Aespa) - Armageddon",
                                                    "에스파(Aespa) - Whiplash",
                                                    "아이브(IVE) - I Am",
                                                    "아이브(IVE) - Attitude",
                                                    "르세라핌(Le Sserafim) - Hot",
                                                    "트와이스(Twice) - Takedown",
                                                    "세븐틴(Seventeen) - Thunder",
                                                    "여자아이들(G-I-dle) - 나는 아픈 건 딱 질색이니까",
                                                    "제니(Jennie) - Mantara",
                                                    "로제(ROSÉ) & Bruno Mars - APT.",
                                                    "Bruno Mars & Lady Gaga - Die With A Smile",
                                                    "정국 ft Latto - Seven",
                                                    "방탄소년단(BTS) - 봄날",
                                                    "임영웅 - 사랑은 늘 도망가",
                                                    "박재정 - 헤어지자 말해요",
                                                    "폴킴 - 모든 날, 모든 순간"));
    genreMap.put("TOP 100", Arrays.asList("헌트릭스(Huntrix) - Golden",
                                                "사자 보이즈(Saja Boys) - Soda Pop",
                                                "블랙핑크(Blackpink) - 뛰어",
                                                "올데이 프로젝트(Allday Project) - Famous",
                                                "우즈(WOODZ) - Drowing",
                                                "에스파(Aespa) - Dirty Work",
                                                "마크툽(Maktub) - 시작의 아이",
                                                "10cm - 너에게 닿기를",
                                                "사자 보이즈(Saja Boys) - Your Idol",
                                                "조째즈 - 모르시나요",
                                                "올데이 프로젝트(Allday Project) - Wicked",
                                                "우디(Woody) - 어제보다 슬픈 오늘",
                                                "에스파(Aespa) - Whiplash",
                                                "제니(Jennie) - Like Jennie",
                                                "지드래곤 ft 태양, 대성 - Home Sweet Home",
                                                "이무진 - 청춘만화",
                                                "아이유(IU) - Never Ending Story",
                                                "헌트릭스(Huntrix) - How It's Done",
                                                "황가람 - 나는 반딧불",
                                                "로제(ROSÉ) & Bruno Mars - APT.",
                                                "지드래곤(G-dragon) ft Anderson Pakk - Too Bad",
                                                "데이식스(Day6) - Happy",
                                                "아이브(IVE) - Rebel Heart",
                                                "보이넥스트도어(BoyNextDoor) - 오늘만 I Love You",
                                                "미야오(Meovv) - Hands Up",
                                                "QWER - 눈물참기",
                                                "아일릿(Illit) - 빌려온 고양이",
                                                "데이식스(Day6) - 한 페이지가 될 수 있게",
                                                "하츠투하츠(Hearts2Hearts) - Style",
                                                "이창섭 - 천상연",
                                                "프로미스나인(Promis9) - Like You Better",
                                                "이클립스(Eclipse) - 소나기",
                                                "에스파(Aespa) - Supernova",
                                                "로이킴(Roykim) - 내게 사랑이 뭐냐고 물어본다면",
                                                "로제(ROSÉ) - Toxic Till The End",
                                                "오반 - Flower",
                                                "데이식스(Day6) - Welcome To The Show",
                                                "이예은, 아샤트리, 전건호 - My Love",
                                                "Bruno Mars & Lady Gaga - Die With A Smile",
                                                "데이식스(Day6) - 예뻤어",
                                                "먼데이 키즈 & 이이경 - 운명(2025)",
                                                "악뮤(Akmu) - 어떻게 이별까지 사랑하겠어",
                                                "TWS - 첫 만남은 계획대로 되지 않아",
                                                "하이키(H1-Key) - 여름이었다",
                                                "QWER - 고민중독",
                                                "QWER - 내 이름 맑음",
                                                "피프티 피프티(Fifty Fifty) - Pookie",
                                                "르세라핌(Le Sserafim) - Hot",
                                                "너드커넥션(Nerd Connection) - 그대만 있다면",
                                                "베이비몬스터(BabyMonster) - Drip",
                                                "헌트릭스(Huntrix) - Free",
                                                "뉴진스(NewJeans) - Hype Boy",
                                                "아이브(IVE) - I Am",
                                                "아이유(IU) - 네모의 꿈",
                                                "트와이스(Twice) - Takedown",
                                                "뉴진스(NewJeans) - How Sweet",
                                                "카리나(Karina) - Up",
                                                "아이브(IVE) - Attitude",
                                                "프로미스나인(Fromis_9) - Supersonic",
                                                "아일릿(ILLIT) - Magnetic",
                                                "투모로우바이투게더 - 그날이 오면",
                                                "이무진 - 청혼하지 않을 이유를 못 찾았어",
                                                "아이유(IU) - Love Wins All",
                                                "잔나비 - 주저하는 연인들을 위해",
                                                "이무진 - 에피소드",
                                                "순순희 - 슬픈 초대장",
                                                "지드래곤(G-Dragon) - Power",
                                                "키키(Kii Kii) - I Do Me",
                                                "성시경 - 너의 모든 순간",
                                                "임재현 - 비의 랩소디",
                                                "멜로망스 - 사랑인가 봐",
                                                "여자아이들(G-I-dle) - 나는 아픈 건 딱 질색이니까",
                                                "키스 오브 라이프(Kiss Of Life) - Sticky",
                                                "황가람 - 미치게 그리워서",
                                                "폴킴 - 모든 날, 모든 순간",
                                                "경서예지 & 전건호 - 다정히 내 이름을 부르면",
                                                "정국 ft Latto - Seven",
                                                "세븐틴(Seventeen) - Thunder",
                                                "박재정 - 헤어지자 말해요",
                                                "임영웅 - 사랑은 늘 도망가",
                                                "범진 - 인사",
                                                "이영지 ft 도경수 - Small Girl",
                                                "우디(Woody) - 사막에서 꽃을 피우듯",
                                                "보이넥스트도어(BoyNextDoor) - -78",
                                                "지드래곤(G-dragon) - 무제",
                                                "헌트릭스(Huntrix) - What It Sounds Like",
                                                "하츠투하츠(Hearts2Hearts) - The Chase",
                                                "헌트릭스(Huntrix) - Takedown",
                                                "방탄소년단(BTS) - 봄날",
                                                "도경수 - 영원해",
                                                "지드래곤(G-dragon) - Take Me",
                                                "라이즈(Riize) - Fly Up",
                                                "김민석 - 취중고백",
                                                "에스파(Aespa) - Armageddon",
                                                "임영웅 - 우리들의 블루스",
                                                "뉴진스(NewJeans) - Ditto",
                                                "플레이브(Plave) - Dash",
                                                "제니(Jennie) - Mantara",
                                                "임영웅 - 다시 만날 수 있을까",
                                                "이찬혁 - 멸종위기사랑"));
    genreMap.put("발라드", Arrays.asList("마크툽(Maktub) - 시작의 아이",
                                                "10cm - 너에게 닿기를",
                                                "우디(Woody) - 어제보다 슬픈 오늘",
                                                "이무진 - 청춘만화",
                                                "황가람 - 나는 반딧불",
                                                "로이킴(Roykim) - 내게 사랑이 뭐냐고 물어본다면",
                                                "오반 - Flower",
                                                "이예은, 아샤트리, 전건호 - My Love",
                                                "먼데이 키즈 & 이이경 - 운명(2025)",
                                                "악뮤(Akmu) - 어떻게 이별까지 사랑하겠어",
                                                "이무진 - 청혼하지 않을 이유를 못 찾았어",
                                                "잔나비 - 주저하는 연인들을 위해",
                                                "이무진 - 에피소드",
                                                "순순희 - 슬픈 초대장",
                                                "성시경 - 너의 모든 순간",
                                                "임재현 - 비의 랩소디",
                                                "멜로망스 - 사랑인가 봐",
                                                "폴킴 - 모든 날, 모든 순간",
                                                "경서예지 & 전건호 - 다정히 내 이름을 부르면",
                                                "박재정 - 헤어지자 말해요",
                                                "임영웅 - 사랑은 늘 도망가",
                                                "범진 - 인사",
                                                "도경수 - 영원해",
                                                "김민석 - 취중고백",
                                                "임영웅 - 우리들의 블루스",
                                                "임영웅 - 다시 만날 수 있을까",
                                                "이찬혁 - 멸종위기사랑"));
    genreMap.put("댄스", Arrays.asList("블랙핑크(Blackpink) - 뛰어",
                                                "에스파(Aespa) - Dirty Work",
                                                "에스파(Aespa) - Whiplash",
                                                "제니(Jennie) - Like Jennie",
                                                "아이브(IVE) - Rebel Heart",
                                                "보이넥스트도어(BoyNextDoor) - 오늘만 I Love You",
                                                "아일릿(Illit) - 빌려온 고양이",
                                                "하츠투하츠(Hearts2Hearts) - Style",
                                                "프로미스나인(Promis9) - Like You Better",
                                                "에스파(Aespa) - Supernova",
                                                "로제(ROSÉ) - Toxic Till The End",
                                                "데이식스(Day6) - Welcome To The Show",
                                                "데이식스(Day6) - 예뻤어",
                                                "QWER - 고민중독",
                                                "QWER - 내 이름 맑음",
                                                "피프티 피프티(Fifty Fifty) - Pookie",
                                                "르세라핌(Le Sserafim) - Hot",
                                                "베이비몬스터(BabyMonster) - Drip",
                                                "뉴진스(NewJeans) - Hype Boy",
                                                "아이브(IVE) - I Am",
                                                "트와이스(Twice) - Takedown",
                                                "뉴진스(NewJeans) - How Sweet",
                                                "카리나(Karina) - Up",
                                                "아이브(IVE) - Attitude",
                                                "프로미스나인(Fromis_9) - Supersonic",
                                                "아일릿(ILLIT) - Magnetic",
                                                "투모로우바이투게더 - 그날이 오면",
                                                "아이유(IU) - Love Wins All",
                                                "여자아이들(G-I-dle) - 나는 아픈 건 딱 질색이니까",
                                                "키스 오브 라이프(Kiss Of Life) - Sticky",
                                                "세븐틴(Seventeen) - Thunder",
                                                "보이넥스트도어(BoyNextDoor) - -78",
                                                "헌트릭스(Huntrix) - Takedown",
                                                "방탄소년단(BTS) - 봄날",
                                                "라이즈(Riize) - Fly Up",
                                                "에스파(Aespa) - Armageddon",
                                                "뉴진스(NewJeans) - Ditto",
                                                "플레이브(Plave) - Dash",
                                                "제니(Jennie) - Mantara"));
    genreMap.put("힙합", Arrays.asList("우즈(WOODZ) - Drowing",
                                                "지드래곤 ft 태양, 대성 - Home Sweet Home",
                                                "지드래곤(G-dragon) ft Anderson Pakk - Too Bad",
                                                "미야오(Meovv) - Hands Up",
                                                "하이키(H1-Key) - 여름이었다",
                                                "너드커넥션(Nerd Connection) - 그대만 있다면",
                                                "헌트릭스(Huntrix) - Golden",
                                                "헌트릭스(Huntrix) - How It's Done",
                                                "헌트릭스(Huntrix) - Free",
                                                "헌트릭스(Huntrix) - What It Sounds Like",
                                                "하츠투하츠(Hearts2Hearts) - The Chase",
                                                "지드래곤(G-Dragon) - Power",
                                                "지드래곤(G-dragon) - 무제",
                                                "지드래곤(G-dragon) - Take Me",
                                                "정국 ft Latto - Seven",
                                                "이영지 ft 도경수 - Small Girl",
                                                "우디(Woody) - 사막에서 꽃을 피우듯",
                                                "키키(Kii Kii) - I Do Me"));
    genreMap.put("POP", Arrays.asList("사자 보이즈(Saja Boys) - Soda Pop",
                                                "사자 보이즈(Saja Boys) - Your Idol",
                                                "올데이 프로젝트(Allday Project) - Famous",
                                                "올데이 프로젝트(Allday Project) - Wicked",
                                                "조째즈 - 모르시나요",
                                                "로제(ROSÉ) & Bruno Mars - APT.",
                                                "Bruno Mars & Lady Gaga - Die With A Smile",
                                                "TWS - 첫 만남은 계획대로 되지 않아",
                                                "QWER - 눈물참기",
                                                "아이유(IU) - Never Ending Story",
                                                "아이유(IU) - 네모의 꿈",
                                                "황가람 - 미치게 그리워서"));
<<<<<<< HEAD
        return genreMap.getOrDefault(genre, new ArrayList<>());
    }

    // static 블록에서 노래 데이터 추가
    static {
        // 노래제목, 가수 딕셔너리
        addSong(artistMap, "헌트릭스(Huntrix)", "Golden");
        addSong(artistMap, "사자 보이즈(Saja Boys)", "Soda Pop");
        addSong(artistMap, "블랙핑크(Blackpink)", "뛰어");
        addSong(artistMap, "올데이 프로젝트(Allday Project)", "Famous");
        addSong(artistMap, "우즈(WOODZ)", "Drowing");
        addSong(artistMap, "에스파(Aespa)", "Dirty Work");
        addSong(artistMap, "마크툽(Maktub)", "시작의 아이");
        addSong(artistMap, "10cm", "너에게 닿기를");
        addSong(artistMap, "사자 보이즈(Saja Boys)", "Your Idol");
        addSong(artistMap, "조째즈", "모르시나요");
        addSong(artistMap, "올데이 프로젝트(Allday Project)", "Wicked");
        addSong(artistMap, "우디(Woody)", "어제보다 슬픈 오늘");
        addSong(artistMap, "에스파(Aespa)", "Whiplash");
        addSong(artistMap, "제니(Jennie)", "Like Jennie");
        addSong(artistMap, "지드래곤 ft 태양, 대성", "Home Sweet Home");
        addSong(artistMap, "이무진", "청춘만화");
        addSong(artistMap, "아이유(IU)", "Never Ending Story");
        addSong(artistMap, "헌트릭스(Huntrix)", "How It's Done");
        addSong(artistMap, "황가람", "나는 반딧불");
        addSong(artistMap, "로제(ROSÉ) & Bruno Mars", "APT.");
        addSong(artistMap, "지드래곤(G-dragon) ft Anderson Pakk", "Too Bad");
        addSong(artistMap, "데이식스(Day6)", "Happy");
        addSong(artistMap, "아이브(IVE)", "Rebel Heart");
        addSong(artistMap, "보이넥스트도어(BoyNextDoor)", "오늘만 I Love You");
        addSong(artistMap, "미야오(Meovv)", "Hands Up");
        addSong(artistMap, "QWER", "눈물참기");
        addSong(artistMap, "아일릿(Illit)", "빌려온 고양이");
        addSong(artistMap, "데이식스(Day6)", "한 페이지가 될 수 있게");
        addSong(artistMap, "하츠투하츠(Hearts2Hearts)", "Style");
        addSong(artistMap, "이창섭", "천상연");
        addSong(artistMap, "프로미스나인(Promis9)", "Like You Better");
        addSong(artistMap, "이클립스(Eclipse)", "소나기");
        addSong(artistMap, "에스파(Aespa)", "Supernova");
        addSong(artistMap, "로이킴(Roykim)", "내게 사랑이 뭐냐고 물어본다면");
        addSong(artistMap, "로제(ROSÉ)", "Toxic Till The End");
        addSong(artistMap, "오반", "Flower");
        addSong(artistMap, "데이식스(Day6)", "Welcome To The Show");
        addSong(artistMap, "이예은, 아샤트리, 전건호", "My Love");
        addSong(artistMap, "Bruno Mars & Lady Gaga", "Die With A Smile");
        addSong(artistMap, "데이식스(Day6)", "예뻤어");
        addSong(artistMap, "먼데이 키즈 & 이이경", "운명(2025)");
        addSong(artistMap, "악뮤(Akmu)", "어떻게 이별까지 사랑하겠어");
        addSong(artistMap, "TWS", "첫 만남은 계획대로 되지 않아");
        addSong(artistMap, "하이키(H1-Key)", "여름이었다");
        addSong(artistMap, "QWER", "고민중독");
        addSong(artistMap, "QWER", "내 이름 맑음");
        addSong(artistMap, "피프티 피프티(Fifty Fifty)", "Pookie");
        addSong(artistMap, "르세라핌(Le Sserafim)", "Hot");
        addSong(artistMap, "너드커넥션(Nerd Connection)", "그대만 있다면");
        addSong(artistMap, "베이비몬스터(BabyMonster)", "Drip");
        addSong(artistMap, "헌트릭스(Huntrix)", "Free");
        addSong(artistMap, "뉴진스(NewJeans)", "Hype Boy");
        addSong(artistMap, "아이브(IVE)", "I Am");
        addSong(artistMap, "아이유(IU)", "네모의 꿈");
        addSong(artistMap, "트와이스(Twice)", "Takedown");
        addSong(artistMap, "뉴진스(NewJeans)", "How Sweet");
        addSong(artistMap, "카리나(Karina)", "Up");
        addSong(artistMap, "아이브(IVE)", "Attitude");
        addSong(artistMap, "프로미스나인(Fromis_9)", "Supersonic");
        addSong(artistMap, "아일릿(ILLIT)", "Magnetic");
        addSong(artistMap, "투모로우바이투게더", "그날이 오면");
        addSong(artistMap, "이무진", "청혼하지 않을 이유를 못 찾았어");
        addSong(artistMap, "아이유(IU)", "Love Wins All");
        addSong(artistMap, "잔나비", "주저하는 연인들을 위해");
        addSong(artistMap, "이무진", "에피소드");
        addSong(artistMap, "순순희", "슬픈 초대장");
        addSong(artistMap, "지드래곤(G-Dragon)", "Power");
        addSong(artistMap, "키키(Kii Kii)", "I Do Me");
        addSong(artistMap, "성시경", "너의 모든 순간");
        addSong(artistMap, "임재현", "비의 랩소디");
        addSong(artistMap, "멜로망스", "사랑인가 봐");
        addSong(artistMap, "여자아이들(G-I-dle)", "나는 아픈 건 딱 질색이니까");
        addSong(artistMap, "키스 오브 라이프(Kiss Of Life)", "Sticky");
        addSong(artistMap, "황가람", "미치게 그리워서");
        addSong(artistMap, "폴킴", "모든 날, 모든 순간");
        addSong(artistMap, "경서예지 & 전건호", "다정히 내 이름을 부르면");
        addSong(artistMap, "정국 ft Latto", "Seven");
        addSong(artistMap, "세븐틴(Seventeen)", "Thunder");
        addSong(artistMap, "박재정", "헤어지자 말해요");
        addSong(artistMap, "임영웅", "사랑은 늘 도망가");
        addSong(artistMap, "범진", "인사");
        addSong(artistMap, "이영지 ft 도경수", "Small Girl");
        addSong(artistMap, "우디(Woody)", "사막에서 꽃을 피우듯");
        addSong(artistMap, "보이넥스트도어(BoyNextDoor)", "-78");
        addSong(artistMap, "지드래곤(G-dragon)", "무제");
        addSong(artistMap, "헌트릭스(Huntrix)", "What It Sounds Like");
        addSong(artistMap, "하츠투하츠(Hearts2Hearts)", "The Chase");
        addSong(artistMap, "헌트릭스(Huntrix)", "Takedown");
        addSong(artistMap, "방탄소년단(BTS)", "봄날");
        addSong(artistMap, "도경수", "영원해");
        addSong(artistMap, "지드래곤(G-dragon)", "Take Me");
        addSong(artistMap, "라이즈(Riize)", "Fly Up");
        addSong(artistMap, "김민석", "취중고백");
        addSong(artistMap, "에스파(Aespa)", "Armageddon");
        addSong(artistMap, "임영웅", "우리들의 블루스");
        addSong(artistMap, "뉴진스(NewJeans)", "Ditto");
        addSong(artistMap, "플레이브(Plave)", "Dash");
        addSong(artistMap, "제니(Jennie)", "Mantara");
        addSong(artistMap, "임영웅", "다시 만날 수 있을까");
        addSong(artistMap, "이찬혁", "멸종위기사랑");

        // 역산
        for (String artist : artistMap.keySet()) {
            for (String song : artistMap.get(artist)) {
                songToArtistMap.put(song, artist);
            }
        }
    }

    // [수정] GUI를 위한 통합 검색 메서드
    public static Map<String, Object> searchSongs(String query, String searchType) {
        // 결과를 담을 Map 객체 생성
        Map<String, Object> results = new HashMap<>();
        if (query == null || query.trim().isEmpty()) {
            // String s = "  abc ";
            return results;
        }

        if ("title".equals(searchType)) {
            String closestSong = findClosest(songToArtistMap.keySet(), query);
            if (closestSong != null) {
                String artistOfSong = songToArtistMap.get(closestSong);

                // 1. 'mainResult' 라는 키로 메인 결과(가장 유사한 1곡) 저장
                results.put("mainResult", artistOfSong + " - " + closestSong);

                // 2. 'otherSongs' 라는 키로 같은 가수의 다른 노래 목록 저장
                List<String> otherSongs = new ArrayList<>();
                for (String song : artistMap.get(artistOfSong)) {
                    if (!song.equals(closestSong)) { // 메인 결과와 중복되지 않도록
                        otherSongs.add(artistOfSong + " - " + song);
                    }
                }
                results.put("otherSongs", otherSongs);
            }
        } else if ("artist".equals(searchType)) {
            String closestArtist = findClosest(artistMap.keySet(), query);
            if (closestArtist != null) {
                List<String> artistSongs = new ArrayList<>();
                for (String song : artistMap.get(closestArtist)) {
                    artistSongs.add(closestArtist + " - " + song);
                }
                // 가수 검색 결과는 'otherSongs' 키에 모두 저장
                results.put("otherSongs", artistSongs);
            }
        }
        return results; // 결과가 담긴 Map 반환
    }

    // 노래 추가 헬퍼 메서드
    private static void addSong(Map<String, List<String>> map, String artist, String song) {
        map.computeIfAbsent(artist, k -> new ArrayList<>()).add(song);
    }

    // 루벤슈타인 거리 계산
    private static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else {
                    int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + cost
=======
    return genreMap.getOrDefault(genre, new ArrayList<>());
}

    // 노래 추가 메서드
    static void addSong(Map<String, List<String>> map, String artist, String song){
        if (!map.containsKey(artist)){
            map.put(artist, new ArrayList<>());
        }
        map.get(artist).add(song);
    }

    // Levenshtein 거리 계산 (오타/유사 검색용)
    static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j - 1],
                        Math.min(dp[i - 1][j], dp[i][j - 1])
>>>>>>> c6c6f13 (password delete)
                    );
                }
            }
        }
        return dp[a.length()][b.length()];
    }

<<<<<<< HEAD
    // [이동] 가장 비슷한 항목 찾기 메서드 (클래스 레벨로 이동)
    private static String findClosest(Set<String> data, String input) {
        String bestMatch = null;
        int bestDistance = Integer.MAX_VALUE;

        // 임계값 설정 (너무 다른 단어는 매칭하지 않도록)
        int threshold = (input.length() / 2) + 2;

        for (String item : data) {
            int dist = levenshteinDistance(input, item);
            if (dist < bestDistance) {
                bestDistance = dist;
                bestMatch = item;
            }
        }
        return (bestDistance <= threshold) ? bestMatch : null;
=======
    // 입력값과 가장 유사한 가수명 찾기
    static String findClosestArtist(Map<String, List<String>> map, String input) {
        String bestMatch = null;
        int bestDistance = Integer.MAX_VALUE;

        for (String artist : map.keySet()) {
            int dist = levenshteinDistance(input, artist);
            if (dist < bestDistance) {
                bestDistance = dist;
                bestMatch = artist;
            }
        }
        return bestMatch;
>>>>>>> c6c6f13 (password delete)
    }

    // artistMap 반환 (외부에서 전체 노래 리스트 조회용)
    public static Map<String, List<String>> getArtistMap() {
        return artistMap;
    }
}