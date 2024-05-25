package view;

import java.util.Scanner;

public class MenuViewer {
    public static Scanner choice = new Scanner(System.in);

    // 메인 메뉴
    public static void mainMenuView() {
        System.out.println();
        System.out.println("===== Mini Football Market =====");
        System.out.println("Select the menu.");
        System.out.println("1. Sign Up"); // 회원가입
        System.out.println("2. Log In"); // 로그인
        System.out.println("3. Run as Administrator"); // 관리자
        System.out.println("4. Quit"); // 종료
        System.out.print("select >> ");
    }

    // 회원가입 메뉴
    public static void signUpMenuView() {
        System.out.println();
        System.out.println("===== Sign Up =====");
        System.out.println("Select the menu.");
        System.out.println("1. Sign Up"); // 회원가입 하기
        System.out.println("2. Return to Main"); // 메인으로 돌아가기
        System.out.print("select >> ");
    }

    // 유저 메뉴
    public static void logInMenuView() {
        System.out.println();
        System.out.println("===== My Menu =====");
        System.out.println("Select the menu.");
        System.out.println("1. Manage My Team"); // 내 팀 관리
        System.out.println("2. Transfer Market"); // 이적시장
        System.out.println("3. Manage My Account"); // 내 계정 정보 수정
        System.out.println("4. Return to Main"); // 메인으로 돌아가기
        System.out.print("select >> ");
    }

    // 유저 메뉴 - 나의팀관리 메뉴
    public static void customMenuView() {
        System.out.println();
        System.out.println("===== Custom Menu =====");
        System.out.println("Select the menu.");
        System.out.println("1. My Player List"); // 내 선수 목록
        System.out.println("2. Release Player"); // 내 선수 방출
        System.out.println("3. My Balance"); // 내 잔고 확인
        System.out.println("4. Go Back"); // 뒤로 돌아가기
        System.out.print("select >> ");
    }

    // 유저 메뉴 - 이적시장 메뉴
    public static void transferMenuView() {
        System.out.println();
        System.out.println("===== Transfer Market =====");
        System.out.println("Select the menu.");
        System.out.println("1. Market List of Players"); // 이적시장 선수리스트
        System.out.println("2. Buy Player"); // 선수 구매
        System.out.println("3. Sell Player"); // 내 선수 판매
        System.out.println("4. My Player List"); // 내 선수 목록
        System.out.println("5. My Balance"); // 내 잔고 확인
        System.out.println("6. Go Back"); // 뒤로 돌아가기
        System.out.print("select >> ");
    }

    // 유저 메뉴 - 계정 정보 관리 메뉴
    public static void memberMenuView() {
        System.out.println();
        System.out.println("===== Account Management =====");
        System.out.println("Select the menu.");
        System.out.println("1. Show My Info"); // 내 계정 정보
        System.out.println("2. Change My Password"); // 비밀번호 수정
        System.out.println("3. Delete My Account"); // 계정 탈퇴
        System.out.println("4. Go Back"); // 뒤로 돌아가기
        System.out.print("select >> ");
    }

    // 관리자 메뉴
    public static void adminMenuView() {
        System.out.println();
        System.out.println("===== Administrator Menu =====");
        System.out.println("1. Create Club"); // 클럽 추가
        System.out.println("2. Delete Club"); // 클럽 삭제
        System.out.println("3. Create Player"); // 선수 추가
        System.out.println("4. Delete Player"); // 선수 삭제
        System.out.println("5. Show Club List"); // 클럽 목록
        System.out.println("6. Show Player List"); // 선수 목록
        System.out.println("7. Show Member List"); // 멤버 목록
        System.out.println("8. Return to Main"); // 뒤로 돌아가기
        System.out.print("select >> ");
    }

}
