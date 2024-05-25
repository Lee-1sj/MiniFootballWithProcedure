package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.PlayerVO;

public class PlayerManager {
    public static Scanner sc = new Scanner(System.in);

    // 선수 추가
    public void createPlayer() {
        String p_name;
        String p_backno;
        String position;
        int shoot;
        int pass;
        int defend;
        int price;
        int c_no;
        PlayerVO pv = new PlayerVO();
        ClubDAO cd = new ClubDAO();
        PlayerDAO pd = new PlayerDAO();

        try {
            pd.getPlayerFromMarket(); // 현재의 선수목록 출력

            System.out.println();
            System.out.println("Enter the info of the Player you want to create.");
            System.out.print("Player Name >> ");
            p_name = sc.nextLine();
            System.out.print("Back No. >> ");
            p_backno = sc.nextLine();
            System.out.print("Position >> ");
            position = sc.nextLine();
            System.out.print("Shoot Stat >> ");
            shoot = sc.nextInt();
            sc.nextLine();
            System.out.print("Pass Stat >> ");
            pass = sc.nextInt();
            sc.nextLine();
            System.out.print("Defend Stat >> ");
            defend = sc.nextInt();
            sc.nextLine();
            System.out.print("Player Price >> ");
            price = sc.nextInt();
            sc.nextLine();
            cd.getClubTotalList(); // 클럽 전체 리스트 출력
            System.out.print("Club No. >> ");
            c_no = sc.nextInt();
            sc.nextLine();

            pv.setP_name(p_name);
            pv.setP_backno(p_backno);
            pv.setP_position(position);
            pv.setP_shoot(shoot);
            pv.setP_pass(pass);
            pv.setP_defend(defend);
            pv.setP_price(price);
            pv.setC_no(c_no);

            pd.setPlayer(pv); // 새 선수 입력
            pd.getOnePlayer(p_name, c_no);
        } catch (InputMismatchException e) {
            e.printStackTrace();
            sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // end of createPlayer()

    public void deletePlayer() {
        int p_no;
        PlayerDAO pd = new PlayerDAO();
        try {
            pd.getPlayerFromMarket(); // 현재의 선수목록 출력

            System.out.println();
            System.out.println("Enter Player No. you want to delete.");
            System.out.print("Player No. >> ");
            p_no = sc.nextInt();
            sc.nextLine();

            pd.eliminatePlayer(p_no);
            pd.getPlayerFromMarket(); // 현재의 선수목록 출력
        } catch (InputMismatchException e) {
            e.printStackTrace();
            sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // end of deletePlayrer()

}
