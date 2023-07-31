package com.example.fastfood.model;

public class LoaiSanPham {
    private String id;
    private String tenloai;

    public LoaiSanPham(String id, String tenloai) {
        this.id = id;
        this.tenloai = tenloai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
