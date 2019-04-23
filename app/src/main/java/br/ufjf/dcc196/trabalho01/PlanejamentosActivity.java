package br.ufjf.dcc196.trabalho01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class PlanejamentosActivity extends AppCompatActivity {
    public static final int REQUEST_PLANEJAMENTO = 1;
    public static final int REQUEST_MATERIA = 2;

    ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> materias = new ArrayList<ArrayList<String>>();


    TableAdapter tAdapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planejamentos_activity);

        ArrayList<String> l = new ArrayList<String>();
        l.add("Testando");
        matriz.add(l);

        System.out.println(matriz);
        for (int j=0; j<6; j++) {
            l.add("test");
        }

        System.out.println(matriz);

        Button btnPlanejamento = findViewById(R.id.btnPlanejamento);
        Button btnMateria = findViewById(R.id.btnMateria);
        Button btnAtt = findViewById(R.id.btnAtualizar);

        btnAtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv = findViewById(R.id.rvTable);
                tAdapter = new TableAdapter(matriz);
                rv.setAdapter(tAdapter);
                rv.setLayoutManager(new LinearLayoutManager(PlanejamentosActivity.this));

                tAdapter.setListener(new TableAdapter.OnPalavraClickListener() {
                    @Override
                    public void onPalavraClick(View v, int position) {
                        String msn = String.valueOf(matriz.get(position));
                        Toast.makeText(PlanejamentosActivity.this, msn, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(PlanejamentosActivity.this, NovaDisciplinaCursadaActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });




        btnPlanejamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanejamentosActivity.this, NovoPlanejamentoActivity.class);
                startActivityForResult(intent, PlanejamentosActivity.REQUEST_PLANEJAMENTO);
            }
        });


        btnMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanejamentosActivity.this, NovaDisciplinaCursadaActivity.class);
                startActivityForResult(intent, PlanejamentosActivity.REQUEST_MATERIA);
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            if (requestCode == PlanejamentosActivity.REQUEST_PLANEJAMENTO && resultCode == PlanejamentosActivity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                ArrayList<String> planejamento = new ArrayList<String>();
                planejamento = (ArrayList<String>) bundle.get("planejamento");
                this.matriz.add(planejamento);
                System.out.println(this.matriz);
            }
            else if (requestCode == PlanejamentosActivity.REQUEST_MATERIA && resultCode == PlanejamentosActivity.RESULT_OK){
                Bundle bundle = data.getExtras();
                ArrayList<String> materia = new ArrayList<>();
                materia = (ArrayList<String>) bundle.get("materia");
                this.materias.add(materia);

            }
        }
    }
}
