package model;

public class PlayerVO {
    private int p_no;
    private String p_name;
    private String p_backno;
    private String p_position;
    private int p_shoot;
    private int p_pass;
    private int p_defend;
    private int p_price;
    private int c_no;

    public int getP_no() {
        return p_no;
    }

    public void setP_no(int p_no) {
        this.p_no = p_no;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_backno() {
        return p_backno;
    }

    public void setP_backno(String p_backno) {
        this.p_backno = p_backno;
    }

    public String getP_position() {
        return p_position;
    }

    public void setP_position(String p_position) {
        this.p_position = p_position;
    }

    public int getP_shoot() {
        return p_shoot;
    }

    public void setP_shoot(int p_shoot) {
        this.p_shoot = p_shoot;
    }

    public int getP_pass() {
        return p_pass;
    }

    public void setP_pass(int p_pass) {
        this.p_pass = p_pass;
    }

    public int getP_defend() {
        return p_defend;
    }

    public void setP_defend(int p_defend) {
        this.p_defend = p_defend;
    }

    public int getP_price() {
        return p_price;
    }

    public void setP_price(int p_price) {
        this.p_price = p_price;
    }

    public int getC_no() {
        return c_no;
    }

    public void setC_no(int c_no) {
        this.c_no = c_no;
    }

    @Override
    public String toString() {
        return "Player No." + p_no + ", " + p_name + ", Back No." + p_backno + ", " + p_position
                + ", Shoot " + p_shoot + ", Pass " + p_pass + ", Defend " + p_defend + ", Price " + p_price
                + "M, Club No." + c_no;
    }

}
