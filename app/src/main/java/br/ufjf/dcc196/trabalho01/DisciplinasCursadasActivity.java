package br.ufjf.dcc196.trabalho01;

import android.annotation.SuppressLint;
import android.os.Parcelable;
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
    public ArrayList<Disciplinas> materias;

    //Table2Adapter tAdapter;
    DisciplinasAdapter dAdapter;
    RecyclerView rv;

    private void rvAtt (){
        rv = findViewById(rvTabela);
        rv.setAdapter(dAdapter);
        rv.setLayoutManager(new LinearLayoutManager(DisciplinasCursadasActivity.this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplinas_cursadas);

        Bundle bundleExtras = getIntent().getExtras();
        if(bundleExtras!=null){
            bundleExtras.getString("disciplinas");
            ArrayList<Disciplinas> dados = (ArrayList<Disciplinas>) getIntent().getSerializableExtra("dados");
            materias = dados;
        }


        Button btnAtt = findViewById(R.id.btn);

        btnAtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvAtt();
            }
        });

        dAdapter = new DisciplinasAdapter(this.materias);
    }
}
