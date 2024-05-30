package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import model.PlayerVO;

public class OwnerDAO {
    // 구매한 선수와 멤버를 맵핑테이블에 저장
    public void savePlayerMember(int p_no, String memberId) {
        String sql = "{CALL SAVE_PLAYER_MEMBER(?, ?, ?)}";
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            con = DBUtil.getConnection();
            cstmt = con.prepareCall(sql);
            cstmt.setInt(1, p_no);
            cstmt.setString(2, memberId);
            cstmt.registerOutParameter(3, Types.INTEGER); // Register out parameter
            cstmt.execute();

            int result = cstmt.getInt(3); // Get the value of the OUT parameter
            if (result == 1) {
                System.out.println("Purchase & Save Complete."); 
                System.out.println();
                System.out.println("----------------------------------------");
                System.out.println();
            } else {
                System.out.println("Purchase & Save Failed.");
                System.out.println();
                System.out.println("----------------------------------------");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(cstmt, con);
        }
    }// end of savePlayerMember()

    // 오너테이블에서 멤버의 보유 선수 list > 객체를 ArrayList에 저장해서 리턴함
    public ArrayList<PlayerVO> getMemberPlayerList(String memberId) {
        ArrayList<PlayerVO> memberPlayerList = null;
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            String sql = "{call GET_MEMBER_PLAYER_LIST(?, ?)}";
            cstmt = con.prepareCall(sql);
            cstmt.setString(1, memberId);
            cstmt.registerOutParameter(2, java.sql.Types.REF_CURSOR);
            cstmt.execute();

            rs = (ResultSet) cstmt.getObject(2);
            memberPlayerList = new ArrayList<>();
            while (rs.next()) {
                PlayerVO pv = new PlayerVO();
                pv.setP_no(rs.getInt("p_no"));
                pv.setP_name(rs.getString("p_name"));
                pv.setP_backno(rs.getString("p_backno"));
                pv.setP_position(rs.getString("p_position"));
                pv.setP_shoot(rs.getInt("p_shoot"));
                pv.setP_pass(rs.getInt("p_pass"));
                pv.setP_defend(rs.getInt("p_defend"));
                pv.setP_price(rs.getInt("p_price"));
                pv.setC_no(rs.getInt("c_no"));
                memberPlayerList.add(pv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(rs, cstmt, con);
        }
        return memberPlayerList;
    }// end of getMemberPlayerList()

    // 맵핑 테이블에서 선택한 선수 삭제
    public void deletePlayerMember(int p_no, String memberId) {
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            con = DBUtil.getConnection();
            String sql = "{call DELETE_PLAYER_MEMBER(?, ?, ?)}";
            cstmt = con.prepareCall(sql);
            cstmt.setInt(1, p_no);
            cstmt.setString(2, memberId);
            cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
            cstmt.execute();

            int result = cstmt.getInt(3);
            if (result == 0) {
                System.out.println(p_no + ". Player Delete Success.");
                System.out.println();
                System.out.println("----------------------------------------");
            } else {
                System.out.println("Delete Failed.");
                System.out.println();
                System.out.println("----------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(cstmt, con);
        }
    }// end of deletePlayerMember()

}
