package com.example.avs14_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.AsyncTask;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mInsert(View view) {
        MyTask myTask = new MyTask();
        myTask.execute();
    }

    public void mList(View view) {
        Intent iEstudantesList = new Intent(this, EstudantesList.class);
        startActivity(iEstudantesList);
    }

    public class MyTask extends AsyncTask<String, Void, String>  {

        @Override
        public void onPreExecute()
        {
            load = ProgressDialog.show(MainActivity.this, "Por favor Aguarde ...",
                    "Salvando dados...");
        }

        @Override
        public String doInBackground(String... params)
        {
            String returning = "";

            try {
                EditText etName = findViewById(R.id.etName);
                EditText etEmail = findViewById(R.id.etEmail);
                EditText etMatricula = findViewById(R.id.etMatricula);

                Estudante estudante = new Estudante(
                        etName.getText().toString(),
                        etEmail.getText().toString(),
                        Integer.parseInt(etMatricula.getText().toString())
                );

                EstudanteDAO estudanteDAO = new EstudanteDAO(MainActivity.this);
                estudanteDAO.inserir(estudante);

                returning = "Estudante Cadastado com sucesso!";

            } catch (Exception e) {
                returning = "Algo de errado aconteceu, tente novamente: "+ e.getMessage();
            }
            
            return returning;
        }

        @Override
        public void onPostExecute(String retorno)
        {
            load.dismiss();
            Toast.makeText(MainActivity.this, retorno, Toast.LENGTH_LONG).show();
        }
    }
}