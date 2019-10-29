package net.hailm.quanly.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.hailm.quanly.R;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> {
    private List<String> mSpinnerArray;
    private Context mContext;
        private int mSelected = -1;

    public SpinnerAdapter(@NonNull Context context, int resourceID, List<String> spinnerArray) {
        super(context, resourceID, spinnerArray);
        this.mContext = context;
        this.mSpinnerArray = spinnerArray;
    }

    public void setSelected(int mSelected) {
        this.mSelected = mSelected;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewSpinner = inflater.inflate(R.layout.spinner_item, parent, false);
        TextView main_text = viewSpinner.findViewById(R.id.tv_data);
        main_text.setText(mSpinnerArray.get(position));

        TextView tvCd = viewSpinner.findViewById(R.id.tv_cd);
        tvCd.setText(mSpinnerArray.get(position));

        if (position == mSelected) {
            viewSpinner.setBackgroundResource(R.color.very_light_grey);
        }

        return viewSpinner;
    }
}
