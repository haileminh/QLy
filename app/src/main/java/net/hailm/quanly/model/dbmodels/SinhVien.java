package net.hailm.quanly.model.dbmodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class SinhVien extends RealmObject implements Parcelable{
    @PrimaryKey
    private int maSv;
    @Required
    private String tenSv;

    private boolean sex;
    private int maKhoa;
    private int maCn;
    private String email;
    private String sdt;
    private String diaChi;
    private String ghiChu;
    private RealmList<KhoaHoc> khoaHocs;
    private RealmList<ChuyenNganh> chuyenNganhs;

    @Ignore
    private String tenKhoa;
    @Ignore
    private String tenChuyenNganh;

    public SinhVien() {
    }

    protected SinhVien(Parcel in) {
        maSv = in.readInt();
        tenSv = in.readString();
        sex = in.readByte() != 0;
        maKhoa = in.readInt();
        maCn = in.readInt();
        email = in.readString();
        sdt = in.readString();
        diaChi = in.readString();
        ghiChu = in.readString();
        tenKhoa = in.readString();
        tenChuyenNganh = in.readString();
    }

    public static final Creator<SinhVien> CREATOR = new Creator<SinhVien>() {
        @Override
        public SinhVien createFromParcel(Parcel in) {
            return new SinhVien(in);
        }

        @Override
        public SinhVien[] newArray(int size) {
            return new SinhVien[size];
        }
    };

    public int getMaSv() {
        return maSv;
    }

    public void setMaSv(int maSv) {
        this.maSv = maSv;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(int maKhoa) {
        this.maKhoa = maKhoa;
    }

    public int getMaCn() {
        return maCn;
    }

    public void setMaCn(int maCn) {
        this.maCn = maCn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public RealmList<KhoaHoc> getKhoaHocs() {
        return khoaHocs;
    }

    public void setKhoaHocs(RealmList<KhoaHoc> khoaHocs) {
        this.khoaHocs = khoaHocs;
    }

    public RealmList<ChuyenNganh> getChuyenNganhs() {
        return chuyenNganhs;
    }

    public void setChuyenNganhs(RealmList<ChuyenNganh> chuyenNganhs) {
        this.chuyenNganhs = chuyenNganhs;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getTenChuyenNganh() {
        return tenChuyenNganh;
    }

    public void setTenChuyenNganh(String tenChuyenNganh) {
        this.tenChuyenNganh = tenChuyenNganh;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(maSv);
        dest.writeString(tenSv);
        dest.writeByte((byte) (sex ? 1 : 0));
        dest.writeInt(maKhoa);
        dest.writeInt(maCn);
        dest.writeString(email);
        dest.writeString(sdt);
        dest.writeString(diaChi);
        dest.writeString(ghiChu);
        dest.writeString(tenKhoa);
        dest.writeString(tenChuyenNganh);
    }
}
