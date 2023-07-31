package com.example.fastfood.model;

public class KhachHang {
    private String makh;
    private String tenkh;

    public KhachHang(String makh, String tenkh) {
        this.makh = makh;
        this.tenkh = tenkh;
    }

    public KhachHang() {

    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }
}
