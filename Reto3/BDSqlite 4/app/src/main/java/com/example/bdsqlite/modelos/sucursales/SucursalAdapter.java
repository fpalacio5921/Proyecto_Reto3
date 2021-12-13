package com.example.bdsqlite.modelos.sucursales;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bdsqlite.R;

import java.util.ArrayList;

public class SucursalAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sucursal> listaSucursals;
    LayoutInflater inflater;

    public SucursalAdapter(Context context, ArrayList<Sucursal> listaSucursals) {
        this.context = context;
        this.listaSucursals = listaSucursals;
    }

    @Override
    public int getCount() {
        return listaSucursals.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        ImageView imgItem = convertView.findViewById(R.id.imgItem);
        TextView tvId = convertView.findViewById(R.id.tvID);
        TextView tvCampo1 = convertView.findViewById(R.id.tvCampo1);
        TextView tvCampo2 = convertView.findViewById(R.id.tvCampo2);
        TextView tvCampo3 = convertView.findViewById(R.id.tvCampo3);

        Sucursal sucursal = listaSucursals.get(position);
        byte[] image = sucursal.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);

        imgItem.setImageBitmap(bitmap);
        tvId.setText(sucursal.getId());
        tvCampo1.setText(sucursal.getName());
        tvCampo2.setText(sucursal.getDirection());
        tvCampo3.setText(sucursal.getPhone());


        return convertView;
    }
}
