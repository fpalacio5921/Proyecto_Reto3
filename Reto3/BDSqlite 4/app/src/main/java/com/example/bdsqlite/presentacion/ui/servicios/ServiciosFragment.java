package com.example.bdsqlite.presentacion.ui.servicios;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bdsqlite.R;
import com.example.bdsqlite.casos_uso.CasoUsoUserData;
import com.example.bdsqlite.databinding.FragmentServiciosBinding;
import com.example.bdsqlite.datos.DBHelper;
import com.example.bdsqlite.modelos.servicios.Servicio;
import com.example.bdsqlite.modelos.servicios.ServicioAdapter;
import com.example.bdsqlite.presentacion.MainActivity;

import java.util.ArrayList;


public class ServiciosFragment extends Fragment {

    private FragmentServiciosBinding binding;
    private DBHelper dbHelper;
    private GridView gridView;
    private ArrayList<Servicio> listaServicios;
    private String type = "SERVICIOS";
    private CasoUsoUserData casoUsoUserData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_servicios,container,false);
        try {
            binding = FragmentServiciosBinding.inflate(inflater, container, false);
            casoUsoUserData = new CasoUsoUserData();
            dbHelper = new DBHelper(getContext());
            gridView = (GridView) root.findViewById(R.id.gridViewServicios);

            Cursor cursor = dbHelper.getData(type);
            listaServicios = casoUsoUserData.llenarListaServicio(cursor);
            ServicioAdapter servicioAdapter = new ServicioAdapter(root.getContext(), listaServicios);
            gridView.setAdapter(servicioAdapter);
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }


        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("type", "SERVICIOS");
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}