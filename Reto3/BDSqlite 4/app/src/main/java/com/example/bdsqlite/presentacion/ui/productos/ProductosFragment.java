package com.example.bdsqlite.presentacion.ui.productos;

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
import com.example.bdsqlite.databinding.FragmentProductosBinding;
import com.example.bdsqlite.datos.DBHelper;
import com.example.bdsqlite.modelos.productos.Producto;
import com.example.bdsqlite.modelos.productos.ProductoAdapter;
import com.example.bdsqlite.presentacion.MainActivity;

import java.util.ArrayList;


public class ProductosFragment extends Fragment {

    private FragmentProductosBinding binding;
    private DBHelper dbHelper;
    private GridView gridView;
    private ArrayList<Producto> listaProductos;
    private String type = "PRODUCTOS";
    private CasoUsoUserData casoUsoUserData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_productos,container,false);
        try {
            binding = FragmentProductosBinding.inflate(inflater, container, false);
            casoUsoUserData = new CasoUsoUserData();
            dbHelper = new DBHelper(getContext());
            gridView = (GridView) root.findViewById(R.id.gridViewProductos);

            Cursor cursor = dbHelper.getData(type);
            listaProductos = casoUsoUserData.llenarListaProducto(cursor);
            ProductoAdapter productoAdapter = new ProductoAdapter(root.getContext(), listaProductos);
            gridView.setAdapter(productoAdapter);
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
                intent.putExtra("type", "PRODUCTOS");
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