package br.ufjf.dcc196.trabalho01;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class NovaDisciplinaCursadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_disciplina_cursada);

        Toast.makeText(getApplicationContext(),"NovaDisciplinaCursadaActivity criada", Toast.LENGTH_SHORT).show();

        Button btnCadastra = findViewById(R.id.btnCadastraMateria);

        btnCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etNome = findViewById(R.id.etNomeMateria);
                EditText etTotalHoras = findViewById(R.id.etTotalHoras);
                EditText etArea = findViewById(R.id.etArea);
                ArrayList<String> materia = new ArrayList<String>();
                materia.add(etNome.getText().toString());
                materia.add(etTotalHoras.getText().toString());
                materia.add(etArea.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("materia", materia);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
