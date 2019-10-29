package net.hailm.quanly;

import net.hailm.quanly.model.dbmodels.SinhVien;

public interface HandlerButton {
    void setOnClickView(SinhVien sinhVien);

    void setOnClickDelete(SinhVien sinhVien);
}
