import controller.ClubDAO;
import controller.ClubManager;
import controller.MemberDAO;
import controller.MemberManager;
import controller.OwnerManager;
import controller.PlayerDAO;
import controller.PlayerManager;
import view.ACCOUNT_CHOICE;
import view.ADMIN_CHOICE;
import view.CUSTOM_CHOICE;
import view.MEMBER_CHOICE;
import view.MENU_CHOICE;
import view.MenuViewer;
import view.SIGN_CHOICE;
import view.TRANSFER_CHOICE;

public class MiniFootballMain {
    public static void main(String[] args) {
        mainMenu();
    } // end of main

    public static void mainMenu() {
        int choiceNum;

        while (true) {
            try {
                MenuViewer.mainMenuView();
                choiceNum = MenuViewer.choice.nextInt();
                MenuViewer.choice.nextLine();

                switch (choiceNum) {
                    case MENU_CHOICE.SIGN: // 1. 회원가입
                        signUpMenu();
                        break;
                    case MENU_CHOICE.LOGIN: // 2. 로그인
                        logInMenu();
                        break;
                    case MENU_CHOICE.ADMIN: // 3. 관리자
                        adminMenu();
                        break;
                    case MENU_CHOICE.EXIT: // 4. 종료
                        System.out.println("           Exit the program.");
                        System.out.println();
                        return;
                    default:
                        System.out.println("Please only enter the appropriate menu number.");
                        break;
                } // end of switch
            } catch (Exception e) {
                System.out.println(e.toString() + "\nPlease restart the program");
                return;
            }
        } // end of while
    } // end of mainMenu()

    // 관리자 메뉴
    public static void adminMenu() {
        int choice;
        ClubDAO cd = new ClubDAO();
        ClubManager cm = new ClubManager();
        PlayerDAO pd = new PlayerDAO();
        PlayerManager pm = new PlayerManager();
        MemberDAO md = new MemberDAO();
        MemberManager mm = new MemberManager();
        mm.verifyAdmin(); // 관리자 검증
        // 관리자 메뉴 출력

        while (true) {
            MenuViewer.adminMenuView();
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
            switch (choice) {
                case ADMIN_CHOICE.CREATE: // 1. 팀 추가
                    cm.createClub();
                    break;
                case ADMIN_CHOICE.DELETE: // 2. 팀 삭제
                    cm.deleteClub();
                    break;
                case ADMIN_CHOICE.MAKE: // 3. 선수 추가
                    pm.createPlayer();
                    break;
                case ADMIN_CHOICE.ELIMINATE: // 4. 선수 삭제
                    pm.deletePlayer();
                    break;
                case ADMIN_CHOICE.CLUBLIST: // 5. 클럽 목록
                    cd.getClubTotalList();
                    break;
                case ADMIN_CHOICE.PLIST: // 6. 선수 목록
                    pd.getPlayerFromMarket();
                    break;
                case ADMIN_CHOICE.MLIST: // 7. 멤버 목록
                    md.getMembers();
                    break;
                case ADMIN_CHOICE.MAIN: // 8. 메인
                    return;
                default:
                    System.out.println("Please only enter the appropriate menu number.");
            }
        }
    } // end of adminMenu()

    // 로그인 메뉴
    public static void logInMenu() {
        MemberManager mm = new MemberManager();
        int choice;
        String memberId = mm.verifyMember(); // 로그인 검증 > memberId를 키값으로 사용하기 위해 리턴

        while (true) {
            MenuViewer.logInMenuView();
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
            switch (choice) {
                case MEMBER_CHOICE.MANAGE: // 나의 팀 관리
                    customMenu(memberId);
                    break;
                case MEMBER_CHOICE.TRANS: // 이적 시장
                    transferMenu(memberId);
                    break;
                case MEMBER_CHOICE.UPDATE: // 나의 정보 관리
                    memberMenu(memberId);
                    break;
                case MEMBER_CHOICE.MAIN: // 메인 메뉴
                    return;
                default:
                    System.out.println("Please only enter the appropriate menu number.");
            } // end of switch
        }
    } // end of logInMenu()

    // 나의 팀 관리 메뉴
    public static void customMenu(String memberId) {
        int choice;
        OwnerManager om = new OwnerManager();
        MemberDAO md = new MemberDAO();
        boolean exitFlag = false;

        while (!exitFlag) {
            MenuViewer.customMenuView();
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
            switch (choice) {
                case CUSTOM_CHOICE.LIST: // 내 팀 선수 목록
                    om.showMyPlayers(memberId);
                    break;
                case CUSTOM_CHOICE.DELETE: // 내 팀 선수 방출
                    om.releasePlayerMember(memberId);
                    break;
                case CUSTOM_CHOICE.BALANCE: // 잔고 확인
                    md.getMemberBalance(memberId);
                    break;
                case CUSTOM_CHOICE.BACK: // 뒤로 가기
                    exitFlag = true;
                    break;
                default:
                    System.out.println("Please only enter the appropriate menu number.");
            }
        }
    } // end of customMenu()

    // 이적 시장 메뉴
    public static void transferMenu(String memberId) {
        int choice;
        PlayerDAO pd = new PlayerDAO();
        OwnerManager om = new OwnerManager();
        MemberDAO md = new MemberDAO();

        while (true) {
            MenuViewer.transferMenuView();
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
            switch (choice) {
                case TRANSFER_CHOICE.LIST: // 이적시장 선수목록
                    pd.getPlayerFromMarket();
                    break;
                case TRANSFER_CHOICE.BUY: // 이적시장 선수구매
                    om.buyPlayerFromMarket(memberId);
                    break;
                case TRANSFER_CHOICE.SELL: // 이적시장 선수판매
                    om.sellPlayerFromMarket(memberId);
                    break;
                case TRANSFER_CHOICE.MYLIST: // 보유한 선수목록
                    om.showMyPlayers(memberId);
                    break;
                case TRANSFER_CHOICE.BALANCE: // 잔고확인
                    md.getMemberBalance(memberId);
                    break;
                case TRANSFER_CHOICE.BACK: // 뒤로 가기
                    return;
                default:
                    System.out.println("Please only enter the appropriate menu number.");
            }
        }
    } // end of transferMenuView()

    // 나의 정보 관리
    public static void memberMenu(String memberId) {
        int choice;
        MemberManager mm = new MemberManager();

        while (true) {
            MenuViewer.memberMenuView();
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
            switch (choice) {
                case ACCOUNT_CHOICE.SHOW: // 계정 정보 보기
                    mm.showMyAccountInfo(memberId);
                    break;
                case ACCOUNT_CHOICE.UPDATE: // 비밀번호 수정
                    mm.updateMyAccountPW(memberId);
                    break;
                case ACCOUNT_CHOICE.DELETE: // 계정 탈퇴
                    mm.deleteMyAccount(memberId);
                    break;
                case ACCOUNT_CHOICE.BACK: // 메인 메뉴
                    return;
                default:
                    System.out.println("Please only enter the appropriate menu number.");
            } // end of switch
        }
    } // end of memberMenu()

    // 회원가입 메뉴
    public static void signUpMenu() {
        int choice;
        MemberManager mm = new MemberManager();

        while (true) {
            MenuViewer.signUpMenuView();
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
            switch (choice) {
                case SIGN_CHOICE.SIGN: // 1. 회원가입
                    System.out.println();
                    mm.signUpMember();
                    return;
                case SIGN_CHOICE.MAIN: // 2. 메인으로 돌아가기
                    return;
                default:
                    System.out.println("Please only enter the appropriate menu number.");
            } // end of switch
        }
    } // end of signUpMenu()

} // end of class