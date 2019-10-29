package net.hailm.quanly.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hailm.quanly.HandlerButton;
import net.hailm.quanly.R;
import net.hailm.quanly.model.dbmodels.SinhVien;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.ViewHolder> {
    private Context context;
    private List<SinhVien> sinhVienList;
    private LayoutInflater inflater;
    private HandlerButton handlerButton;

    public void setHandlerButton(HandlerButton handlerButton) {
        this.handlerButton = handlerButton;
    }

    public SinhVienAdapter(Context context, List<SinhVien> sinhVienList, HandlerButton handlerButton) {
        this.context = context;
        this.sinhVienList = sinhVienList;
        inflater = LayoutInflater.from(context);
        this.handlerButton = handlerButton;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_sinhvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final SinhVien sinhVien = sinhVienList.get(position);
        holder.txtInfor.setText(sinhVien.getMaSv() + " - " + sinhVien.getTenSv());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerButton.setOnClickView(sinhVien);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                handlerButton.setOnClickDelete(sinhVien);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return sinhVienList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtInfor;

        public ViewHolder(View itemView) {
            super(itemView);
            txtInfor = itemView.findViewById(R.id.txt_infor);
        }
    }
}
