package br.ufjf.dcc196.trabalho01;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import static br.ufjf.dcc196.trabalho01.R.id.rvTabela;

public class DisciplinasCursadasActivity extends AppCompatActivity {

    public ArrayList<ArrayList<String>> materias = new ArrayList<ArrayList<String>>();

    //materias.add({"2016", "1", "Matematica", "10", "Exatas"});

    Table2Adapter tAdapter;
    RecyclerView rv;

    private void rvAtt (){
        rv = findViewById(rvTabela);
        rv.setAdapter(tAdapter);
        rv.setLayoutManager(new LinearLayoutManager(DisciplinasCursadasActivity.this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplinas_cursadas);

        Bundle bundleExtras = getIntent().getExtras();
        if(bundleExtras!=null){
            bundleExtras.getString("materias");
            ArrayList<String> a = (ArrayList<String>) getIntent().getSerializableExtra("materias");
            materias.add(a);
        }


        Button btnAtt = findViewById(R.id.btn);

        btnAtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvAtt();
            }
        });

        tAdapter = new Table2Adapter(this.materias);

        tAdapter.setListener(new Table2Adapter.OnPalavra2ClickListener() {
            @Override
            public void onPalavra2Click(View v, int position) {
                String msn = String.valueOf(materias.get(position));
                Toast.makeText(DisciplinasCursadasActivity.this, msn, Toast.LENGTH_LONG).show();
            }
        });
    }
}
