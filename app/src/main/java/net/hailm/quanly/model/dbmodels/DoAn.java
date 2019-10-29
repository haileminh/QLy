package net.hailm.quanly.model.dbmodels;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class DoAn extends RealmObject {
    @PrimaryKey
    private int maSv;
    @Required
    private String tenDa;
    private int giaoVienHD;
    private int giaoVienPB;
    private int maLv;
    private double diem;
    private int namTotNghiep;

    public int getMaSv() {
        return maSv;
    }

    public void setMaSv(int maSv) {
        this.maSv = maSv;
    }

    public String getTenDa() {
        return tenDa;
    }

    public void setTenDa(String tenDa) {
        this.tenDa = tenDa;
    }

    public int getGiaoVienHD() {
        return giaoVienHD;
    }

    public void setGiaoVienHD(int giaoVienHD) {
        this.giaoVienHD = giaoVienHD;
    }

    public int getGiaoVienPB() {
        return giaoVienPB;
    }

    public void setGiaoVienPB(int giaoVienPB) {
        this.giaoVienPB = giaoVienPB;
    }

    public int getMaLv() {
        return maLv;
    }

    public void setMaLv(int maLv) {
        this.maLv = maLv;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public int getNamTotNghiep() {
        return namTotNghiep;
    }

    public void setNamTotNghiep(int namTotNghiep) {
        this.namTotNghiep = namTotNghiep;
    }
}
