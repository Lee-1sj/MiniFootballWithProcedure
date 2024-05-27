package controller;

import java.util.Scanner;

public class ClubManager {
    public static Scanner sc = new Scanner(System.in);

    // 팀 생성
    public void createClub() {
        String c_name;
        ClubDAO cd = new ClubDAO();

        try {
            cd.getClubTotalList(); // 클럽 전체 리스트 출력
            System.out.println();
            System.out.print("Enter a new club name >> ");
            c_name = sc.nextLine();

            cd.createNewClub(c_name); // 추가
            cd.getClubTotalList(); // 클럽 전체 리스트 출력
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end of createClub()

    // 팀 삭제
    public void deleteClub() {
        String c_name;
        ClubDAO cd = new ClubDAO();
        try {
            cd.getClubTotalList(); // 클럽 전체 리스트 출력
            System.out.println();
            System.out.print("Enter club name you want to delete >> ");
            c_name = sc.nextLine();

            cd.eliminateClub(c_name); // 제거
            cd.getClubTotalList(); // 클럽 전체 리스트 출력
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end of deleteClub()

}
