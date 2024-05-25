package controller;

import java.util.Scanner;

import model.MemberVO;

public class MemberManager {
    public static Scanner sc = new Scanner(System.in);

    // 회원가입 기능구현
    public void signUpMember() {
        MemberVO mvo = new MemberVO();
        MemberDAO md = new MemberDAO();
        ClubDAO cd = new ClubDAO();

        String m_id; // 멤버 id
        String m_pw; // 멤버 pw
        String m_email; // 멤버 email
        int c_no; // 팀 일련번호
        boolean id_check;
        boolean email_check;

        System.out.println("Enter membership information.");
        try {
            do {
                // id 입력
                System.out.print("Input ID(Character limit 4~16) >> ");
                m_id = sc.nextLine();
                // id 중복체크
                id_check = md.getMemberIdOverlap(m_id);
                if (id_check) {
                    System.out.println("This is a duplicate ID. Please re-enter it."); // id 중복 시 출력문
                }
            } while (id_check);
            // pw 입력
            System.out.print("Input Password(Character limit 4~16) >> ");
            m_pw = sc.nextLine();
            do {
                // email 입력
                System.out.print("Input Email Account >> ");
                m_email = sc.nextLine();
                // email 중복체크
                email_check = md.getMemberEmailOverlap(m_email);
                if (email_check) {
                    System.out.println("This is a duplicate Email. Please re-enter it."); // email 중복 시 출력문
                }
            } while (email_check);
            // 초기 팀 선택을 위한 팀 리스트 출력
            cd.getClubTotalList();
            // 초기 팀 입력
            System.out.print("Choose the initial team >> ");
            c_no = sc.nextInt();
            sc.nextLine();

            mvo.setM_id(m_id);
            mvo.setM_pw(m_pw);
            mvo.setM_email(m_email);
            mvo.setC_no(c_no);
            md.setMemberRegister(mvo);
            // 가입 회원 정보 출력
            System.out.println();
            System.out.println("=================== New Member Info ====================");
            md.getMember(mvo.getM_id(), mvo.getM_pw()); // 가입한 member 객체만 불러서 정보 출력
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end of singUpMember()

    // 관리자 검증 기능
    public void verifyAdmin() {
        MemberDAO md = new MemberDAO();
        String id;
        String pw;
        boolean isAdmin = false;
        // 관리자 로그인 확인
        System.out.println();
        System.out.println("===== Admin Log In ======");

        try {
            do {
                System.out.print("Input Admin ID >> ");
                id = sc.nextLine();
                System.out.print("Input Admin PW >> ");
                pw = sc.nextLine();

                isAdmin = md.getAdminLogin(id, pw); // 검증
                if (!isAdmin) {
                    System.out.println("The Admin account is not valid. Please re-enter.");
                    System.out.println();
                }
            } while (!isAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // end of verifyAdmin()

    // 로그인 검증 기능
    public String verifyMember() {
        MemberDAO md = new MemberDAO();
        String id;
        String pw;
        boolean success = false;
        // 로그인 확인
        System.out.println();
        System.out.println("===== Log In ======");

        try {
            do {
                System.out.print("Input ID >> ");
                id = sc.nextLine();
                System.out.print("Input PW >> ");
                pw = sc.nextLine();

                success = md.getMemberLogin(id, pw); // 입력받은 값 두 개로 db에서 찾음
                if (!success) {
                    System.out.println("The account information is not valid. Please re-enter.");
                    System.out.println();
                }
            } while (!success);

        } catch (Exception e) {
            e.printStackTrace();
            id = null;
        }
        return id;
    } // end of verifyMember()

    // 멤버 정보 출력
    public void showMyAccountInfo(String memberId) {
        MemberDAO md = new MemberDAO();

        System.out.println();
        System.out.println("=========================== My Info ===========================");
        md.getMemberInfo(memberId);
    } // end of showMyAccountInfo()

    // 비밀번호 수정
    public void updateMyAccountPW(String memberId) {
        MemberManager mm = new MemberManager();
        MemberDAO md = new MemberDAO();
        String newPW;

        try {
            mm.showMyAccountInfo(memberId); // 내 정보 출력
            System.out.println();
            System.out.print("Enter a new Password >> ");
            newPW = sc.nextLine();
            md.changePassword(newPW, memberId); // 비밀번호 변경
            mm.showMyAccountInfo(memberId); // 변경한 내 정보 출력
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end of updateMyAccountInfo()

    // 계정 삭제
    public void deleteMyAccount(String memberId) {
        MemberDAO md = new MemberDAO();
        String id;
        String pw;
        String answer;

        try {
            System.out.println("Enter the ID and PW of the Account you want to delete.");
            System.out.print("ID >> ");
            id = sc.nextLine();
            System.out.print("PW >> ");
            pw = sc.nextLine();
            System.out.println();
            System.out.println("Are you sure you want to delete your account?");
            System.out.println("Press 'Y' to delete | Press any other letter to cancel");
            System.out.print("Press >> ");
            answer = sc.nextLine();

            if (answer.equalsIgnoreCase("Y")) {
                if (id.equals(memberId) && pw.equals(md.getMemberPW(memberId))) {
                    md.deleteMember(memberId);
                } else {
                    System.out.println();
                    System.out.println("Account info does not match. Please try again later.");
                }
            } else {
                System.out.println();
                System.out.println("The delete procedure has been canceled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end of deleteMyAccount()

}
