package com.example.bdsqlite.casos_uso;

import android.database.Cursor;

import com.example.bdsqlite.modelos.productos.Producto;
import com.example.bdsqlite.modelos.servicios.Servicio;
import com.example.bdsqlite.modelos.sucursales.Sucursal;

import java.util.ArrayList;

public class CasoUsoUserData {
    public String cursorToString(Cursor cursor){
        if(cursor.getCount() == 0){
            return "No Hay Datos";
        }else{
            StringBuffer buffer = new StringBuffer();

            while (cursor.moveToNext()){
                buffer.append("Id: "+cursor.getString(0)+"\n");
                buffer.append("Name: "+cursor.getString(1)+"\n");
                buffer.append("Description: "+cursor.getString(2)+"\n");
                buffer.append("Price: "+cursor.getString(3)+"\n\n");
            }
            return buffer.toString();
        }
    }

    public ArrayList<Sucursal> llenarListaSucursal(Cursor cursor){
        ArrayList<Sucursal> list = new ArrayList<>();
        if(cursor.getCount() == 0){
            return list;
        }else{
            while (cursor.moveToNext()){
                Sucursal sucursal = new Sucursal(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getBlob(4)
                );
                list.add(sucursal);
            }
            return list;
        }
    }

    public ArrayList<Producto> llenarListaProducto(Cursor cursor){
        ArrayList<Producto> list = new ArrayList<>();
        if(cursor.getCount() == 0){
            return list;
        }else{
            while (cursor.moveToNext()){
                Producto producto = new Producto(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getBlob(4)
                );
                list.add(producto);
            }
            return list;
        }
    }

    public ArrayList<Servicio> llenarListaServicio(Cursor cursor){
        ArrayList<Servicio> list = new ArrayList<>();
        if(cursor.getCount() == 0){
            return list;
        }else{
            while (cursor.moveToNext()){
                Servicio servicio = new Servicio(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getBlob(4)
                );
                list.add(servicio);
            }
            return list;
        }
    }
}
