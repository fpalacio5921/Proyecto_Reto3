package com.example.bdsqlite.presentacion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bdsqlite.casos_uso.CasoUsoUserData;
import com.example.bdsqlite.datos.DBHelper;
import com.example.bdsqlite.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE_GALLERY = 999;

    private String campo1, campo2, campo3, id;
    private byte[] image;
    private EditText editCampo1, editCampo2, editCampo3, editId;
    private Button btnInsertar, btnConsultar, btnActualizar, btnEliminar, btnGaleria;
    private TextView tvTitulo;
    private ImageView imgSelected;
    private String type;

    private DBHelper dbHelper;
    private CasoUsoUserData casoUsoUserData;

    /**
     * Generate UserByName
     * @param { cursor } cursor
     * @return { void } Set all fields with userInfo
     */
    public void showCursorById(Cursor cursor){
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No hay Datos", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "si hay Datos", Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()){
                editId.setText(cursor.getString(0));
                editCampo1.setText(cursor.getString(1));
                editCampo2.setText(cursor.getString(2));
                editCampo3.setText(cursor.getString(3));
                byte[] image = cursor.getBlob(4);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
                imgSelected.setImageBitmap(bitmap);
            }
        }
    }

    public void llenarCampos(){
        id = editId.getText().toString();
        campo1 = editCampo1.getText().toString();
        campo2 = editCampo2.getText().toString();
        campo3 = editCampo3.getText().toString();
        image = imageViewToByte(imgSelected);
    }

    public void cleanFields(){
        editId.setText("");
        editCampo1.setText("");
        editCampo2.setText("");
        editCampo3.setText("");
        imgSelected.setImageResource(R.mipmap.ic_launcher_round);
    }

    public byte[] imageViewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvTitulo.setText(type);

        editCampo1 = (EditText) findViewById(R.id.editCampo1);
        editCampo2 = (EditText) findViewById(R.id.editCampo2);
        editCampo3 = (EditText) findViewById(R.id.editCampo3);
        editId = (EditText) findViewById(R.id.editId);

        if(type.equals("SUCURSALES")){
            editCampo1.setHint("Name");
            editCampo2.setHint("Direction");
            editCampo3.setHint("Phone");
        }else if(type.equals("PRODUCTOS")){
            editCampo1.setHint("Name");
            editCampo2.setHint("Description");
            editCampo3.setHint("Price");
        }else if(type.equals("SERVICIOS")){
            editCampo1.setHint("Name");
            editCampo2.setHint("Description");
            editCampo3.setHint("Price");
        }

        imgSelected = (ImageView) findViewById(R.id.imgSelected);

        btnInsertar = (Button) findViewById(R.id.btnInsertar);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnGaleria = (Button) findViewById(R.id.btnGaleria);



        dbHelper = new DBHelper(this);
        casoUsoUserData = new CasoUsoUserData();

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });




        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenarCampos();
                dbHelper.insertData(campo1, campo2, campo3, image, type);
                cleanFields();
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String id = editId.getText().toString().trim();
                    Cursor cursor = dbHelper.getDataByID(id, type);
                    showCursorById(cursor);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = editId.getText().toString().trim();
                    dbHelper.deleteData(id, type);
                    cleanFields();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }else{
                Toast.makeText(getApplicationContext(), "Sin Permisos", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgSelected.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}