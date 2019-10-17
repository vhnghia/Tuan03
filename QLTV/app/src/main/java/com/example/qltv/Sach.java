package com.example.qltv;

public class Sach {
    String tenSach;
    String NXB;

    public Sach(){
        super();
    }

    public Sach(String tenSach, String NXB) {
        this.tenSach = tenSach;
        this.NXB = NXB;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }
}
