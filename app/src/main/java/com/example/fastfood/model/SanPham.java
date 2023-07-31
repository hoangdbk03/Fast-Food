package com.example.fastfood.model;

public class SanPham {
    private String masp;
    private String tensp;
    private String maloai;
    private Integer giatien;
    private int soluongmua;
    private String tenloai;

    public SanPham(String masp, String tensp, String maloai, Integer giatien) {
        this.masp = masp;
        this.tensp = tensp;
        this.maloai = maloai;
        this.giatien = giatien;
    }

    public SanPham(String tensp, int soluongmua) {
        this.tensp = tensp;
        this.soluongmua = soluongmua;
    }

    public SanPham(String masp, String tensp, String maloai, Integer giatien, String tenloai) {
        this.masp = masp;
        this.tensp = tensp;
        this.maloai = maloai;
        this.giatien = giatien;
        this.tenloai = tenloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public Integer getGiatien() {
        return giatien;
    }

    public void setGiatien(Integer giatien) {
        this.giatien = giatien;
    }

    public int getSoluongmua() {
        return soluongmua;
    }

    public void setSoluongmua(int soluongmua) {
        this.soluongmua = soluongmua;
    }
}
