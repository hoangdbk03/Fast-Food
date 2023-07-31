package com.example.fastfood.model;

public class HoaDon {
    private int mahdct;
    private String makh;
    private String tenkh;
    private String tensp;
    private int giatien;
    private String ngay;
    private int thanhtoan;
//SELECT ct.mahdct, ct.makh, kh.tenkh, ct.ngaymua, ct.giatien, ct.trangthai


    public HoaDon() {
    }

    public HoaDon(int mahdct, String makh, String tenkh, String tensp, int giatien, String ngay, int thanhtoan) {
        this.mahdct = mahdct;
        this.makh = makh;
        this.tenkh = tenkh;
        this.tensp = tensp;
        this.giatien = giatien;
        this.ngay = ngay;
        this.thanhtoan = thanhtoan;
    }
    public HoaDon(String makh, String tensp, int giatien, String ngay, int thanhtoan) {
        this.makh = makh;
        this.tensp = tensp;
        this.giatien = giatien;
        this.ngay = ngay;
        this.thanhtoan = thanhtoan;
    }

    public int getMahdct() {
        return mahdct;
    }

    public void setMahdct(int mahdct) {
        this.mahdct = mahdct;
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

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(int thanhtoan) {
        this.thanhtoan = thanhtoan;
    }
}
