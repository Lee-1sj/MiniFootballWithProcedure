package model;

public class MemberVO {
    private int m_no; // 멤버 일련번호
    private String m_id; // 멤버 id
    private String m_pw; // 멤버 pw
    private String m_email; // 멤버 email
    private int m_isAdmin; // 관리자 권한 여부
    private int m_balance; // 멤버 잔고
    private int c_no; // 팀 일련번호

    public MemberVO() {
    }

    public MemberVO(int m_no, String m_id, String m_pw, String m_email, int m_isAdmin, int m_balance, int c_no) {
        this.m_no = m_no;
        this.m_id = m_id;
        this.m_pw = m_pw;
        this.m_email = m_email;
        this.m_isAdmin = m_isAdmin;
        this.m_balance = m_balance;
        this.c_no = c_no;
    }

    public int getM_no() {
        return m_no;
    }

    public void setM_no(int m_no) {
        this.m_no = m_no;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_pw() {
        return m_pw;
    }

    public void setM_pw(String m_pw) {
        this.m_pw = m_pw;
    }

    public String getM_email() {
        return m_email;
    }

    public void setM_email(String m_email) {
        this.m_email = m_email;
    }

    public int getM_isAdmin() {
        return m_isAdmin;
    }

    public void setM_isAdmin(int m_isAdmin) {
        this.m_isAdmin = m_isAdmin;
    }

    public int getM_balance() {
        return m_balance;
    }

    public void setM_balance(int m_balance) {
        this.m_balance = m_balance;
    }

    public int getC_no() {
        return c_no;
    }

    public void setC_no(int c_no) {
        this.c_no = c_no;
    }

}
