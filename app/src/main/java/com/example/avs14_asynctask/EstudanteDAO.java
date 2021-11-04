package com.example.avs14_asynctask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EstudanteDAO {

    private Connection con;
    private SQLiteDatabase db;

    public EstudanteDAO(Context context){
        con = new Connection(context);
        db = con.getWritableDatabase();
    }

    public long inserir(Estudante estudante){
        ContentValues contentValues = new ContentValues();

        contentValues.put("nome", estudante.getNome());
        contentValues.put("email", estudante.getEmail());
        contentValues.put("matricula", estudante.getMatricula());

        return db.insert("estudantes", null, contentValues);
    }

    public List<Estudante> list(){
        List<Estudante> list = new ArrayList<Estudante>();

        Cursor cursor = db.rawQuery("SELECT * FROM estudantes", null);

        while (cursor.moveToNext()) {
            list.add(
                    new Estudante(
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getInt(3)
                    )
            );
        }

        return list;
    }

}
