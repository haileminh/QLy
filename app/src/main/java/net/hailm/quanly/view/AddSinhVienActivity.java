package net.hailm.quanly.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import net.hailm.quanly.R;
import net.hailm.quanly.adapter.SpinnerAdapter;
import net.hailm.quanly.model.dbhelpers.SinhVienDbHelper;
import net.hailm.quanly.model.dbmodels.ChuyenNganh;
import net.hailm.quanly.model.dbmodels.KhoaHoc;
import net.hailm.quanly.model.dbmodels.SinhVien;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSinhVienActivity extends AppCompatActivity {
    @BindView(R.id.edt_ten_sv)
    EditText edtTenSv;
    @BindView(R.id.edt_dia_chi)
    EditText edtDiaChi;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_ghi_chú)
    EditText edtGhiChu;
    @BindView(R.id.edt_sdt)
    EditText edtSdt;

    @BindView(R.id.rd_nam)
    RadioButton rdNam;
    @BindView(R.id.rd_nu)
    RadioButton rdNu;

    @BindView(R.id.spn_khoa)
    Spinner spnKhoa;
    @BindView(R.id.spn_chuyen_nganh)
    Spinner spnChuyenNganh;

    private SinhVien sinhVien;
    private SinhVienDbHelper dbHelper;
    private List<String> listKhoaHoc;
    private List<String> listChuyenNganh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinhvien);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        sinhVien = new SinhVien();
        dbHelper = new SinhVienDbHelper();
        listKhoaHoc = new ArrayList<>();
        listChuyenNganh = new ArrayList<>();

        List<KhoaHoc> khoaHocs = dbHelper.getListKhoaHoc();
        for (int i = 0; i < khoaHocs.size(); i++) {
            listKhoaHoc.add(String.valueOf(khoaHocs.get(i).getTenKhoa()));
        }

        List<ChuyenNganh> chuyenNganhs = dbHelper.getChuyenNganh();
        for (int i = 0; i < chuyenNganhs.size(); i++) {
            listChuyenNganh.add(String.valueOf(chuyenNganhs.get(i).getTenCn()));
        }

        spnKhoa.setAdapter(new SpinnerAdapter(this, R.layout.spinner_item, listKhoaHoc));

        spnChuyenNganh.setAdapter(new SpinnerAdapter(this, R.layout.spinner_item, listChuyenNganh));
    }

    @OnClick(R.id.btn_add)
    public void onItemClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                getData();
                try {
                    dbHelper.insert(sinhVien);
                    startActivity(new Intent(AddSinhVienActivity.this, MainActivity.class));
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void getData() {
        sinhVien.setTenSv(edtTenSv.getText().toString());
        if (rdNam.isChecked()) {
            sinhVien.setSex(true);
        } else {
            sinhVien.setSex(false);
        }

        sinhVien.setEmail(edtEmail.getText().toString());
        sinhVien.setSdt(edtSdt.getText().toString());
        sinhVien.setDiaChi(edtDiaChi.getText().toString());
        sinhVien.setGhiChu(edtGhiChu.getText().toString());

        spnKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tenKhoa = spnKhoa.getSelectedItem().toString();
                KhoaHoc khoaHocByName = dbHelper.getKhoaHocByName(tenKhoa);
                sinhVien.setMaKhoa(khoaHocByName.getMaKhoa());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnChuyenNganh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tenCN = spnChuyenNganh.getSelectedItem().toString();
                ChuyenNganh chuyenNganhByName = dbHelper.getChuyenNganhByName(tenCN);
                sinhVien.setMaCn(chuyenNganhByName.getMaCn());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
