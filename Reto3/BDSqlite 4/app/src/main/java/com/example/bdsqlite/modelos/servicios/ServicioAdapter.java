package com.example.bdsqlite.modelos.servicios;

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

public class ServicioAdapter extends BaseAdapter {
    Context context;
    ArrayList<Servicio> listaServicios;
    LayoutInflater inflater;

    public ServicioAdapter(Context context, ArrayList<Servicio> listaServicios) {
        this.context = context;
        this.listaServicios = listaServicios;
    }

    @Override
    public int getCount() {
        return listaServicios.size();
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

        Servicio servicio = listaServicios.get(position);
        byte[] image = servicio.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);

        imgItem.setImageBitmap(bitmap);
        tvId.setText(servicio.getId());
        tvCampo1.setText(servicio.getName());
        tvCampo2.setText(servicio.getDescription());
        tvCampo3.setText(servicio.getPrice());


        return convertView;
    }
}