package net.hailm.quanly.model.dbhelpers;

import net.hailm.quanly.model.RealmManager;
import net.hailm.quanly.model.dbmodels.ChuyenNganh;
import net.hailm.quanly.model.dbmodels.KhoaHoc;
import net.hailm.quanly.model.dbmodels.SinhVien;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class SinhVienDbHelper {
    /**
     * Get all sinh viên by search...
     *
     * @param tvSearch
     * @return
     */
    public List<SinhVien> getAllSinhVien(String tvSearch) {
        List<SinhVien> sinhViens = new ArrayList<>();
        try {
            RealmManager.incrementCount();
            Realm realm = RealmManager.getRealm();

            RealmResults<SinhVien> sinhVienRealmResults = realm.where(SinhVien.class)
                    .like("tenSv", "*" + tvSearch + "*")
                    .findAll();
            sinhViens = realm.copyFromRealm(sinhVienRealmResults);

            for (SinhVien values : sinhViens) {
                setTenKhoaHoc(values, realm);
                setTenChuyenNganh(values, realm);
            }
            return sinhViens;

        } finally {
            RealmManager.decrementCount();
        }
    }

    /**
     * get all sinh vien
     *
     * @return
     */
    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> sinhViens = new ArrayList<>();
        try {
            RealmManager.incrementCount();
            Realm realm = RealmManager.getRealm();

            RealmResults<SinhVien> sinhVienRealmResults = realm.where(SinhVien.class).findAll();
            sinhViens = realm.copyFromRealm(sinhVienRealmResults);

            for (SinhVien values : sinhViens) {
                setTenKhoaHoc(values, realm);
                setTenChuyenNganh(values, realm);
            }
            return sinhViens;

        } finally {
            RealmManager.decrementCount();
        }
    }

    private void setTenChuyenNganh(SinhVien sinhVien, Realm realm) {
        ChuyenNganh chuyenNganh = realm.where(ChuyenNganh.class)
                .equalTo("maCn", sinhVien.getMaCn())
                .findFirst();
        if (chuyenNganh != null) {
            sinhVien.setTenChuyenNganh(chuyenNganh.getTenCn());
        }
    }

    private void setTenKhoaHoc(SinhVien sinhVien, Realm realm) {
        KhoaHoc khoaHoc = realm.where(KhoaHoc.class)
                .equalTo("maKhoa", sinhVien.getMaKhoa())
                .findFirst();
        if (khoaHoc != null) {
            sinhVien.setTenKhoa(khoaHoc.getTenKhoa());
        }
    }

    public List<ChuyenNganh> getChuyenNganh() {
        try {
            RealmManager.incrementCount();
            Realm realm = RealmManager.getRealm();

            RealmResults<ChuyenNganh> chuyenNganhRealmResults = realm.where(ChuyenNganh.class)
                    .sort("maCn", Sort.DESCENDING)
                    .findAll();

            List<ChuyenNganh> chuyenNganhs = realm.copyFromRealm(chuyenNganhRealmResults);

            return chuyenNganhs;
        } finally {
            RealmManager.decrementCount();
        }
    }

    public List<KhoaHoc> getListKhoaHoc() {
        try {
            RealmManager.incrementCount();
            Realm realm = RealmManager.getRealm();

            RealmResults<KhoaHoc> khoaHocRealmResults = realm.where(KhoaHoc.class).findAll();
            List<KhoaHoc> khoaHocs = realm.copyFromRealm(khoaHocRealmResults);

            return khoaHocs;
        } finally {
            RealmManager.decrementCount();
        }
    }

    public KhoaHoc getKhoaHocByName(String name) {
        try {
            RealmManager.incrementCount();
            Realm realm = RealmManager.getRealm();
            KhoaHoc khoaHoc = realm.where(KhoaHoc.class)
                    .equalTo("tenKhoa", name)
                    .findFirst();

            return khoaHoc;
        } finally {
            RealmManager.decrementCount();
        }
    }
    
    public ChuyenNganh getChuyenNganhByName(String name) {
        try {
            RealmManager.incrementCount();
            Realm realm = RealmManager.getRealm();
            ChuyenNganh chuyenNganh = realm.where(ChuyenNganh.class).equalTo("tenCn", name).findFirst();
            return chuyenNganh;
        } finally {
            RealmManager.decrementCount();
        }
    }


    /**
     * Update Sinh vien
     *
     * @param sinhVien
     * @throws Exception
     */
    public void update(final SinhVien sinhVien) throws Exception {
        try {
            RealmManager.incrementCount();
            Realm realm = RealmManager.getRealm();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(sinhVien);
                }
            });

        } finally {
            RealmManager.decrementCount();
        }
    }

    /**
     * Delete Sinh vien
     *
     * @param sinhVien
     * @throws Exception
     */
    public void delete(final SinhVien sinhVien) throws Exception {
        try {
            RealmManager.incrementCount();
            Realm realm = RealmManager.getRealm();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<SinhVien> sinhViens = realm.where(SinhVien.class)
                            .equalTo("maSv", sinhVien.getMaSv())
                            .findAll();
                    sinhViens.deleteAllFromRealm();
                }
            });
        } finally {
            RealmManager.decrementCount();
        }
    }

    /**
     * Insert sinh viên
     *
     * @param sinhVien
     * @throws Exception
     */

    public void insert(final SinhVien sinhVien) throws Exception {
        try {
            RealmManager.incrementCount();
            Realm realm = RealmManager.getRealm();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (realm.where(SinhVien.class).findAll().size() > 0) {
                        int id_new = realm.where(SinhVien.class).max("maSv").intValue() + 1;
                        sinhVien.setMaSv(id_new);
                    } else {
                        sinhVien.setMaSv(1);
                    }
                    realm.insertOrUpdate(sinhVien);
                }
            });

        } finally {
            RealmManager.decrementCount();
        }
    }


}
