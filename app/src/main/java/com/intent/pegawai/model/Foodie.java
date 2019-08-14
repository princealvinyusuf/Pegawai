package com.intent.pegawai.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Foodie implements Parcelable {

    public static final Creator<Foodie> CREATOR = new Creator<Foodie>() {
        @Override
        public Foodie createFromParcel(Parcel source) {
            return new Foodie(source);
        }

        @Override
        public Foodie[] newArray(int size) {
            return new Foodie[size];
        }
    };
    private String title, department, photo;
    private String content, bahan, waktu, cara, lokasi;

    public Foodie() {
    }

    protected Foodie(Parcel in) {
        this.title = in.readString();
        this.department = in.readString();
        this.photo = in.readString();
        this.content = in.readString();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String sistem) {
        this.bahan = sistem;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String ukuran) {
        this.waktu = ukuran;
    }

    public String getCara() {
        return cara;
    }

    public void setCara(String arsitek) {
        this.cara = arsitek;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String remarks) {
        this.department = remarks;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.department);
        dest.writeString(this.photo);
        dest.writeString(this.content);
    }
}
