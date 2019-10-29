package net.hailm.quanly.model.dbmodels;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class LinhVuc extends RealmObject {
    @PrimaryKey
    private int maLv;
    @Required
    private String tenLv;
    private String ghiChu;

    public int getMaLv() {
        return maLv;
    }

    public void setMaLv(int maLv) {
        this.maLv = maLv;
    }

    public String getTenLv() {
        return tenLv;
    }

    public void setTenLv(String tenLv) {
        this.tenLv = tenLv;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
