package net.hailm.quanly.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

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

public class ViewSinhVienActivity extends AppCompatActivity {
    @BindView(R.id.edt_msv)
    EditText edtMasv;
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


    private SinhVien mSinhVien;
    private SinhVienDbHelper dbHelper;
    private List<String> listKhoaHoc;
    private List<String> listChuyenNganh;
    private int selectionChuyenNganh = 0;
    private int selectionKhoaHoc = 0;
    private SinhVien sinhVien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sinh_vien);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        dbHelper = new SinhVienDbHelper();
        listKhoaHoc = new ArrayList<>();
        listChuyenNganh = new ArrayList<>();
        sinhVien = new SinhVien();

        loadData();
        getData();
    }

    private void loadData() {
        mSinhVien = (SinhVien) getIntent().getParcelableExtra("SINHVIEN");

        edtMasv.setText(String.valueOf(mSinhVien.getMaSv()));
        edtTenSv.setEnabled(true);
        edtTenSv.setText(mSinhVien.getTenSv());
        if (mSinhVien.isSex()) {
            rdNam.setChecked(true);
        } else {
            rdNu.setChecked(true);
        }
        edtEmail.setText(mSinhVien.getEmail());
        edtSdt.setText(mSinhVien.getSdt());
        edtDiaChi.setText(mSinhVien.getDiaChi());
        edtGhiChu.setText(mSinhVien.getGhiChu());


        List<KhoaHoc> khoaHocs = dbHelper.getListKhoaHoc();
        for (int i = 0; i < khoaHocs.size(); i++) {
            listKhoaHoc.add(String.valueOf(khoaHocs.get(i).getMaKhoa()));
            if (mSinhVien.getMaKhoa() == khoaHocs.get(i).getMaKhoa()) {
                selectionKhoaHoc = i;
            }
        }

        List<ChuyenNganh> chuyenNganhs = dbHelper.getChuyenNganh();
        for (int i = 0; i < chuyenNganhs.size(); i++) {
            listChuyenNganh.add(String.valueOf(chuyenNganhs.get(i).getMaCn()));
            if (mSinhVien.getMaCn() == chuyenNganhs.get(i).getMaCn()) {
                selectionChuyenNganh = i;
            }
        }

        spnKhoa.setAdapter(new SpinnerAdapter(this, R.layout.spinner_item, listKhoaHoc));
        spnKhoa.setSelection(selectionKhoaHoc);
        spnChuyenNganh.setAdapter(new SpinnerAdapter(this, R.layout.spinner_item, listChuyenNganh));
        spnChuyenNganh.setSelection(selectionChuyenNganh);
    }

    private void getData() {
        sinhVien.setMaSv(mSinhVien.getMaSv());
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
                Toast.makeText(ViewSinhVienActivity.this, spnKhoa.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                sinhVien.setMaKhoa(Integer.parseInt(spnKhoa.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnChuyenNganh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sinhVien.setMaCn(Integer.parseInt(spnChuyenNganh.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.btn_update})
    public void onItemClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                getData();
                try {
                    dbHelper.update(sinhVien);
                    startActivity(new Intent(ViewSinhVienActivity.this, MainActivity.class));
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
