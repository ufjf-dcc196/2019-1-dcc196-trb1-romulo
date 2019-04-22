package br.ufjf.dcc196.trabalho01;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NovoPlanejamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_planejamento);

        Toast.makeText(getApplicationContext(),"NovoPlanejamentoActivitY criada", Toast.LENGTH_SHORT).show();

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
                ArrayList<String> planejamento = new ArrayList<String>();
                planejamento.add(etAno.getText().toString());
                planejamento.add(etSemestre.getText().toString());
                planejamento.add(String.valueOf(count));
                planejamento.add(etLinguas.getText().toString());
                planejamento.add(etHumanas.getText().toString());
                planejamento.add(etExatas.getText().toString());
                planejamento.add(etSaude.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("planejamento", planejamento);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
