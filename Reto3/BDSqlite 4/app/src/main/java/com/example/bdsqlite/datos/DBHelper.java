package com.example.bdsqlite.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBHelper extends SQLiteOpenHelper {
    private final static String nameDataBase = "Miscelanea.db";
    private SQLiteDatabase sqLiteDatabase;

    public String txtPublic;
    public DBHelper(Context context){
        super(context, nameDataBase, null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SUCURSALES(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR, " +
                "direction VARCHAR, " +
                "phone VARCHAR, " +
                "image BLOB)");
        db.execSQL("CREATE TABLE PRODUCTOS(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR, " +
                "description VARCHAR, " +
                "price VARCHAR, " +
                "image BLOB)");
        db.execSQL("CREATE TABLE SERVICIOS(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR, " +
                "description VARCHAR, " +
                "price VARCHAR, " +
                "image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SUCURSALES");
        db.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
        db.execSQL("DROP TABLE IF EXISTS SERVICIOS");
    }

    // **** Funciones o Metodos Propios ****

    public void insertData(String campo1, String campo2, String campo3, byte[] image, String table){
        String sql = "INSERT INTO "+table+" VALUES(null,?,?,?,?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, campo1);
        statement.bindString(2, campo2);
        statement.bindString(3, campo3);
        statement.bindBlob(4, image);

        statement.executeInsert();
    }
    public Cursor getData(String table){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " +table,null);
        return cursor;
    }

    public Cursor getDataByID(String id, String table){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+table+" WHERE id =" + id,null);
        return cursor;
    }

    public void deleteData(String id, String table){
        String[] args = new String[]{id};
        sqLiteDatabase.delete(table,"id=?",args);
    }
}
