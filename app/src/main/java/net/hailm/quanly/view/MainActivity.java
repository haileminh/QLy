package net.hailm.quanly.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;

import net.hailm.quanly.HandlerButton;
import net.hailm.quanly.R;
import net.hailm.quanly.adapter.SinhVienAdapter;
import net.hailm.quanly.model.dbhelpers.SinhVienDbHelper;
import net.hailm.quanly.model.dbmodels.ChuyenNganh;
import net.hailm.quanly.model.dbmodels.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        SearchView.OnQueryTextListener, SearchView.OnCloseListener, HandlerButton {
    private SinhVienDbHelper dbHelper;
    private List<SinhVien> sinhVienList;
    private SinhVienAdapter sinhVienAdapter;
    private RecyclerView rcvSinhVien;
    private FloatingActionButton btnAdd;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new SinhVienDbHelper();
        List<ChuyenNganh> chuyenNganh = dbHelper.getChuyenNganh();
        LogUtils.d("Chuyên ngành: " + chuyenNganh);

        init();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        mSearchView = (SearchView) menuItem.getActionView();
        mSearchView.setQueryHint("Search");
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnCloseListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    private void init() {
        rcvSinhVien = findViewById(R.id.rcv_sinhvien);
        sinhVienList = new ArrayList<>();
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        LinearLayoutManager llm = new LinearLayoutManager(getApplication());
        rcvSinhVien.setLayoutManager(llm);

        setAdapter();
    }

    private void setAdapter() {
        sinhVienList = dbHelper.getAllSinhVien();
        sinhVienAdapter = new SinhVienAdapter(this, sinhVienList, this);
        rcvSinhVien.setAdapter(sinhVienAdapter);
        sinhVienAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                startActivity(new Intent(MainActivity.this, AddSinhVienActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        List<SinhVien> allSinhVien = dbHelper.getAllSinhVien(query);
        sinhVienAdapter = new SinhVienAdapter(this, allSinhVien, this);
        rcvSinhVien.setAdapter(sinhVienAdapter);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.length() == 0) {
            setAdapter();
        }
        return false;
    }

    @Override
    public boolean onClose() {
        setAdapter();
        return false;
    }

    @Override
    public void setOnClickView(SinhVien sinhVien) {
        Intent intent = new Intent(MainActivity.this, ViewSinhVienActivity.class);
        intent.putExtra("SINHVIEN", sinhVien);
        startActivity(intent);
    }

    @Override
    public void setOnClickDelete(SinhVien sinhVien) {
        try {
            dbHelper.delete(sinhVien);
            Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setAdapter();
    }
}
