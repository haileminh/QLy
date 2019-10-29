package net.hailm.quanly.model.dbmodels;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class ChuyenNganh extends RealmObject {
    @PrimaryKey
    private int maCn;
    @Required
    private String tenCn;

    private String ghiChu;

    public int getMaCn() {
        return maCn;
    }

    public void setMaCn(int maCn) {
        this.maCn = maCn;
    }

    public String getTenCn() {
        return tenCn;
    }

    public void setTenCn(String tenCn) {
        this.tenCn = tenCn;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
