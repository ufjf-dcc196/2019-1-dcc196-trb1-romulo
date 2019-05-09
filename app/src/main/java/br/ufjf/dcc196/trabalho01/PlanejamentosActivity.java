    package br.ufjf.dcc196.trabalho01;

    import android.content.Intent;
    import android.os.Bundle;
    import android.os.Parcelable;
    import android.support.annotation.Nullable;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.view.View;
    import android.widget.Button;

    import java.io.Serializable;
    import java.util.ArrayList;

    public class PlanejamentosActivity extends AppCompatActivity {
        public static final int REQUEST_PLANEJAMENTO = 1;
        public static final int REQUEST_MATERIA = 2;

        ArrayList<Planejamentos> planejamentos = new ArrayList<>();

        RecyclerView rv;
        PlanejamentosAdapter pAdapter;

        private void rvAtt (){
            rv = findViewById(R.id.rvTable);
            rv.setAdapter(pAdapter);
            rv.setLayoutManager(new LinearLayoutManager(PlanejamentosActivity.this));
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.planejamentos_activity);

            planejamentos.add(new Planejamentos(2016, 1, 10, 2, 40, 2));
            planejamentos.add(new Planejamentos(2016, 2, 10, 20, 1, 90));

            planejamentos.get(0).setDisciplinas("Matematica", 2, "Exatas");
            planejamentos.get(0).setDisciplinas("Portugues", 3, "Linguas");
            planejamentos.get(0).setDisciplinas("Ingles", 2, "Linguas");
            planejamentos.get(0).setDisciplinas("Historia", 2, "Humanas");
            planejamentos.get(0).setDisciplinas("Biologia", 2, "Saúde");

            planejamentos.get(1).setDisciplinas("Fisica", 3, "Exatas");
            planejamentos.get(1).setDisciplinas("Ingles", 1, "Linguas");
            planejamentos.get(1).setDisciplinas("Geografia", 5, "Humanas");
            planejamentos.get(1).setDisciplinas("Biologia", 7, "Saúde");

            Button btnPlanejamento = findViewById(R.id.btnPlanejamento);
            Button btnMateria = findViewById(R.id.btnMateria);
            Button btnAtt = findViewById(R.id.btnAtualizar);

            btnAtt.setOnClickListener(new View.OnClickListener() {//Atualiza o Recicler View
                @Override
                public void onClick(View v) {
                    rvAtt();
                }
            });

            pAdapter = new PlanejamentosAdapter(planejamentos);

            pAdapter.setListener(new PlanejamentosAdapter.OnPlanejamentoClickListener() {
                @Override
                public void onPlanejamentoClick(View v, int position) {
                    Intent intent = new Intent(PlanejamentosActivity.this, DisciplinasCursadasActivity.class);
                    intent.putExtra("dados", planejamentos.get(position).getDisciplinas());
                    startActivity(intent);
                }
            });

            btnPlanejamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlanejamentosActivity.this, NovoPlanejamentoActivity.class);
                    intent.putExtra("palnejamentos", planejamentos);
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
                    Planejamentos t = new Planejamentos();
                    t.setAno((Integer) bundle.get("ano"));
                    t.setSemestre((Integer) bundle.get("semestre"));
                    t.setHorasLinguas((Float) bundle.get("hLinguas"));
                    t.setHorasHumanas((Float) bundle.get("hHumanas"));
                    t.setHorasExatas((Float) bundle.get("hExatas"));
                    t.setHorasSaude((Float) bundle.get("hSaude"));
                    t.calculaHoras();
                    this.planejamentos.add(t);
                }
                else if (requestCode == PlanejamentosActivity.REQUEST_MATERIA && resultCode == PlanejamentosActivity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    Disciplinas tempD = new Disciplinas();
                    int ano, semestre;
                    tempD.setNome((String) bundle.get("nome"));
                    tempD.setThoras((Float) bundle.get("tHora"));
                    tempD.setArea((String) bundle.get("area"));

                    ano = (int) bundle.get("ano");
                    semestre = (int) bundle.get("semestre");

                    for (int i=0; i<planejamentos.size(); i++){
                        if(planejamentos.get(i).getAno() == ano && planejamentos.get(i).getSemestre() == semestre){
                            planejamentos.get(i).setDisciplinas(tempD);
                        }
                    }
                }
            }
        }
    }
