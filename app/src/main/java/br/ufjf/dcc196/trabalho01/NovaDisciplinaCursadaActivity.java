package br.ufjf.dcc196.trabalho01;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
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
                EditText etAno = findViewById(R.id.etAno);
                EditText etSemestre = findViewById(R.id.etSemestre);

                Disciplinas disc = new Disciplinas();

                disc.setNome((etNome.getText().toString()));
                disc.setThoras(Float.parseFloat((etTotalHoras.getText().toString())));
                disc.setArea((etArea.getText().toString()));

                //Dados para verificar o ano e semestre da materia
                int ano = Integer.valueOf(etAno.getText().toString());
                int semestre = Integer.valueOf(etSemestre.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("nome", disc.getNome());
                intent.putExtra("tHora", disc.getThoras());
                intent.putExtra("area", disc.getArea());
                intent.putExtra("ano", ano);
                intent.putExtra("semestre", semestre);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
