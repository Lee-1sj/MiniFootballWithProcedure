package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import model.MemberVO;

public class MemberDAO {
    // new member 등록
    public void setMemberRegister(MemberVO mvo) {
        String sql = "{CALL MEMBER_REGISTER(?, ?, ?, ?)}";
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            con = DBUtil.getConnection();
            cstmt = con.prepareCall(sql);
            cstmt.setString(1, mvo.getM_id());
            cstmt.setString(2, mvo.getM_pw());
            cstmt.setString(3, mvo.getM_email());
            cstmt.setInt(4, mvo.getC_no());

            int i = cstmt.executeUpdate();
            if (i == 1) {
                System.out.println("Sign Up Complete.");
            } else {
                System.out.println("Sign up Failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } // end of setMemberRegister

    // ID 중복 체크
    public boolean getMemberIdOverlap(String isOverlap_id) {
        String sql = "{call CHECK_MEMBER_ID_OVERLAP(?, ?)}";
        Connection con = null;
        CallableStatement cstmt = null;
        boolean isOverlap = false;

        try {
            con = DBUtil.getConnection();
            cstmt = con.prepareCall(sql);
            cstmt.setString(1, isOverlap_id);
            cstmt.registerOutParameter(2, java.sql.Types.NUMERIC);
            cstmt.execute();
            int result = cstmt.getInt(2);
            if (result > 0) {
                isOverlap = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isOverlap;
    } // end of getMemberIdOverlap()

    // email 중복 체크
    public boolean getMemberEmailOverlap(String isOverlap_email) {
        String sql = "{call CHECK_MEMBER_EMAIL_OVERLAP(?, ?)}";
        Connection con = null;
        CallableStatement cstmt = null;
        boolean isOverlap = false;

        try {
            con = DBUtil.getConnection();
            cstmt = con.prepareCall(sql);
            cstmt.setString(1, isOverlap_email);
            cstmt.registerOutParameter(2, java.sql.Types.NUMERIC);
            cstmt.execute();
            int result = cstmt.getInt(2);
            if (result > 0) {
                isOverlap = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isOverlap;
    } // end of getMemberEmailOverlap()

    // 멤버객체 하나 불러오기 (id, pw 이용)
    public void getMember(String id, String pw) {
        String sql = "{CALL GET_MEMBER(?, ?, ?)}";
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            con = DBUtil.getConnection();
            cstmt = con.prepareCall(sql);
            cstmt.setString(1, id);
            cstmt.setString(2, pw);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.execute();

            String result = cstmt.getString(3);
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } // end of getMember()

    // 로그인 기능
    public boolean getMemberLogin(String id, String pw) {
        String sql = "{CALL MEMBER_LOGIN(?, ?, ?)}";
        Connection con = null;
        CallableStatement cstmt = null;
        boolean loginSuccess = false;

        try {
            con = DBUtil.getConnection();
            cstmt = con.prepareCall(sql);
            cstmt.setString(1, id);
            cstmt.setString(2, pw);
            cstmt.registerOutParameter(3, java.sql.Types.NUMERIC);
            cstmt.execute();

            int result = cstmt.getInt(3);
            if (result == 1) {
                System.out.println();
                System.out.println("login success.");
                loginSuccess = true;
            } else {
                System.out.println();
                System.out.println("login failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loginSuccess;
    } // end of getMemberLogin()

    // 관리자 로그인 기능
    public boolean getAdminLogin(String id, String pw) {
        String sql = "SELECT m_isadmin FROM MEMBERS WHERE m_id = ? AND m_pw = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        int verify;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                verify = rs.getInt("m_isadmin");
                if (verify == 1) {
                    System.out.println();
                    System.out.println("Admin mode success");
                    loginSuccess = true;
                } else {
                    System.out.println();
                    System.out.println("Admin mode failed");
                }
            } else {
                System.out.println();
                System.out.println("No matching account exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loginSuccess;

    } // end of getAdminLogin()

    // memberId를 가지고 db를 찾아서 m_no를 불러옴
    public int getMemberNum(String memberId) {
        String sql = "SELECT m_no FROM MEMBERS WHERE m_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int m_no = -1;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                m_no = rs.getInt("m_no");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return m_no;
    } // end of getMemberNum()

    // 로그인한 id로 멤버의 잔액 조회하는 함수
    public int getMemberBalance(String memberId) {
        String sql = "{call GET_MEMBER_BALANCE(?, ?, ?)}";
        int balance = 0;
        String message = "";
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            con = DBUtil.getConnection();
            cstmt = con.prepareCall(sql);
            cstmt.setString(1, memberId);
            cstmt.registerOutParameter(2, Types.NUMERIC);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.execute();

            balance = cstmt.getInt(2);
            message = cstmt.getString(3);
            System.out.println(message);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return balance;
    } // end of getMemberBalance()

    // 계산후 잔액을 유저 정보에 저장하는 함수
    public void setMemberBalance(int newBalance, String memberId) {
        String sql = "CALL SET_MEMBER_BALANCE(?, ?)";
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            con = DBUtil.getConnection();
            cstmt = con.prepareCall(sql);
            cstmt.setInt(1, newBalance);
            cstmt.setString(2, memberId);
            cstmt.executeUpdate();
            System.out.println("남은 잔액: " + newBalance);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    } // end of setMemberBalance()

    // 멤버 정보 출력
    public void getMemberInfo(String memberId) {
        String sql = "SELECT * FROM members WHERE m_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberVO mvo = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();

            System.out.println("No.\tID\tPW\tE-mail\t\tBalance\tTeam No.");

            if (rs.next()) {
                mvo = new MemberVO();
                mvo.setM_no(rs.getInt("m_no"));
                mvo.setM_id(rs.getString("m_id"));
                mvo.setM_pw(rs.getString("m_pw"));
                mvo.setM_email(rs.getString("m_email"));
                mvo.setM_balance(rs.getInt("m_balance"));
                mvo.setC_no(rs.getInt("c_no"));
            }
            System.out.println(mvo.getM_no() + "\t" + mvo.getM_id() + "\t" + mvo.getM_pw() + "\t" + mvo.getM_email()
                    + "\t" + mvo.getM_balance()
                    + "\t" + mvo.getC_no());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } // end of getMemberInfo()

    // 비밀번호 수정
    public void changePassword(String newPW, String memberId) {
        String sql = "{call CHANGE_PASSWORD(?, ?, ?)}";
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            con = DBUtil.getConnection();
            cstmt = con.prepareCall(sql);
            cstmt.setString(1, newPW);
            cstmt.setString(2, memberId);
            cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
            cstmt.execute();

            int result = cstmt.getInt(3);
            if (result == 1) {
                System.out.println();
                System.out.println("Password Change Complete.");
            } else {
                System.out.println();
                System.out.println("Password Change Failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 멤버 아이디로 비밀번호 가져오기
    public String getMemberPW(String memberId) {
        String sql = "{call GET_MEMBER_PW(?, ?)}";
        String password = "";
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            cstmt = con.prepareCall(sql);
            cstmt.setString(1, memberId);
            cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            cstmt.execute();
            password = cstmt.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cstmt != null) {
                    cstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return password;
    } // end of getMemberPW()

    // 멤버 삭제하기
    public void deleteMember(String memberId) {
        String sql = "{call DELETE_MEMBER(?, ?)}";
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            con = DBUtil.getConnection();
            cstmt = con.prepareCall(sql);
            cstmt.setString(1, memberId);
            cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
            cstmt.execute();

            int result = cstmt.getInt(2);
            if (result == 1) {
                System.out.println();
                System.out.println(memberId + " User Delete Success");
            } else {
                System.out.println();
                System.out.println("Delete Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    } // end of deleteMember()

    // 관리자권한으로 모든 멤버 정보 출력
    public void getMembers() {
        String sql = "SELECT * FROM MEMBERS ORDER BY m_no";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberVO mvo;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.println();
            System.out.println(String.format("%-12s %-15s %-15s %-25s %-15s %-15s %-15s", "MEMBER NO.",
                    "MEMBER ID", "MEMBER PW", "MEMBER E-MAIL", "AUTHORITY", "BALANCE", "CLUB NO."));

            while (rs.next()) {
                mvo = new MemberVO();
                mvo.setM_no(rs.getInt("m_no"));
                mvo.setM_id(rs.getString("m_id"));
                mvo.setM_pw(rs.getString("m_pw"));
                mvo.setM_email(rs.getString("m_email"));
                mvo.setM_isAdmin(rs.getInt("m_isadmin"));
                mvo.setM_balance(rs.getInt("m_balance"));
                mvo.setC_no(rs.getInt("c_no"));

                System.out.println(String.format("%-12s %-15s %-15s %-25s %-15s %-15s %-15s", mvo.getM_no(),
                        mvo.getM_id(), mvo.getM_pw(), mvo.getM_email(), mvo.getM_isAdmin(), mvo.getM_balance(),
                        mvo.getC_no()));

            }
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    } // end of getMembers()

} // end of class
