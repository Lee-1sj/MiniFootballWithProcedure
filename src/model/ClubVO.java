package model;

public class ClubVO {
    private int c_no; // 팀 일련번호
    private String c_name; // 팀 이름

    public ClubVO() {
    }

    public ClubVO(int c_no, String c_name) {
        this.c_no = c_no;
        this.c_name = c_name;
    }

    public int getC_no() {
        return c_no;
    }

    public void setC_no(int c_no) {
        this.c_no = c_no;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    @Override
    public String toString() {
        return "Serial No: " + getC_no() + ", Club Name: " + getC_name();
    }

}
