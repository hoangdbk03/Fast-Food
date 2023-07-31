package com.example.fastfood.model;

public class Recommend {
    String name;
    Integer imageUrl;
    String loai;
    Integer gia;

    public Recommend(String name,Integer imageUrl, String loai, Integer gia) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.loai = loai;
        this.gia = gia;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }
}
