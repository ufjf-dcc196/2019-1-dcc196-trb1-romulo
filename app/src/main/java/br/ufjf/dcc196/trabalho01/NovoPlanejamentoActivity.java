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

public class NovoPlanejamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_planejamento);

        Bundle bundle = getIntent().getExtras();

        Button btnCadastraPlanejamento = findViewById(R.id.btnCadastraPlanejamento);

        btnCadastraPlanejamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etAno = findViewById(R.id.etAno);
                EditText etLinguas = findViewById(R.id.etLingua);
                EditText etHumanas = findViewById(R.id.etHumanas);
                EditText etExatas = findViewById(R.id.etExatas);
                EditText etSaude = findViewById(R.id.etSaude);
                EditText etSemestre = findViewById((R.id.etSemestre));

                int count = Integer.valueOf(etLinguas.getText().toString())+Integer.valueOf(etHumanas.getText().toString())+Integer.valueOf(etExatas.getText().toString())+Integer.valueOf(etSaude.getText().toString());

                Planejamentos planejamento = new Planejamentos();

                planejamento.setAno(Integer.valueOf(String.valueOf(etAno.getText())));
                planejamento.setSemestre(Integer.valueOf(String.valueOf(etSemestre.getText())));
                planejamento.setHorasLinguas(Float.valueOf(etLinguas.getText().toString()));
                planejamento.setHorasHumanas(Float.valueOf(etHumanas.getText().toString()));
                planejamento.setHorasExatas(Float.valueOf(etExatas.getText().toString()));
                planejamento.setHorasSaude(Float.valueOf(etSaude.getText().toString()));

                Intent intent = new Intent();
                intent.putExtra("ano", planejamento.getAno());
                intent.putExtra("semestre", planejamento.getSemestre());
                intent.putExtra("hLinguas", planejamento.getHorasLinguas());
                intent.putExtra("hHumanas", planejamento.getHorasHumanas());
                intent.putExtra("hExatas", planejamento.getHorasExatas());
                intent.putExtra("hSaude", planejamento.getHorasHumanas());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
