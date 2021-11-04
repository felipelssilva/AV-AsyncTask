package com.example.avs14_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class EstudantesList extends AppCompatActivity {

    List<Estudante> lEstudante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudantes_list);

        ListView lvEstudantes = findViewById(R.id.lvEstudantes);
        EstudanteDAO estudanteDAO = new EstudanteDAO(this);
        lEstudante = estudanteDAO.list();

        ArrayAdapter<Estudante> adapter = new ArrayAdapter<Estudante>(this, android.R.layout.simple_list_item_1, lEstudante);
        lvEstudantes.setAdapter(adapter);
    }
}