    package br.ufjf.dcc196.trabalho01;

    import android.content.Intent;
    import android.os.Bundle;
    import android.support.annotation.Nullable;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.view.View;
    import android.widget.Button;

    import java.util.ArrayList;

    public class PlanejamentosActivity extends AppCompatActivity {
        public static final int REQUEST_PLANEJAMENTO = 1;
        public static final int REQUEST_MATERIA = 2;

        ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
        ArrayList<String[]> materias = new ArrayList<>();
        TableAdapter tAdapter;
        RecyclerView rv;

        private void rvAtt (){
            rv = findViewById(R.id.rvTable);
            rv.setAdapter(tAdapter);
            rv.setLayoutManager(new LinearLayoutManager(PlanejamentosActivity.this));
        }

        private float porcentagem(float total, float y){
            float x;
            x = (y*100)/total;
            return x;
        }
        private ArrayList<String> convertePorcentagem(ArrayList<String> a){
            float total = Float.parseFloat(a.get(2));
            float l = Integer.parseInt(a.get(3));
            float h = Float.parseFloat(a.get(4));
            float e = Float.parseFloat(a.get(5));
            float s = Float.parseFloat(a.get(6));
            l = porcentagem(total, l);
            h = porcentagem(total, h);
            e = porcentagem(total, e);
            s = porcentagem(total, s);
            System.out.println(l);
            a.set(3, String.valueOf(l));
            a.set(4, String.valueOf(h));
            a.set(5, String.valueOf(e));
            a.set(6, String.valueOf(s));
            return a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.planejamentos_activity);
            //--------------------------Adicionado dados para teste---------------------------------
            //---------------------------------Planejamento-----------------------------------------
            ArrayList<String> l0 = new ArrayList<String>();
            l0.add("Ano");
            l0.add("Semestre");
            l0.add("Horas");
            l0.add("Linguas (%)");
            l0.add("Humanas (%)");
            l0.add("Exatas (%)");
            l0.add("Saúde (%)");
            matriz.add(l0);
            ArrayList<String> l1 = new ArrayList<String>();
            l1.add("2016");
            l1.add("1");
            l1.add("18");
            l1.add("38.9");
            l1.add("16.7");
            l1.add("27.7");
            l1.add("16.7");
            matriz.add(l1);
            ArrayList<String> l2 = new ArrayList<String>();
            l2.add("2016");
            l2.add("2");
            l2.add("16");
            l2.add("31.25");
            l2.add("18.75");
            l2.add("31.25");
            l2.add("18.75");
            matriz.add(l2);
            //--------------------------------Fim Planjamento---------------------------------------
            //------------------------------------Materias------------------------------------------
            materias.add(new String[]{"Materia", "Horas(h)", "Área", "Ano", "Semestre"});
            materias.add(new String[]{"Matematica", "5", "Exatas", "2016", "3"});
            materias.add(new String[]{"Portugues", "3", "Linguas", "2016", "1"});
            materias.add(new String[]{"Historia", "5", "Humanas", "2017", "3"});
            //-------------------------------Fim Tabela de MAterias---------------------------------
            //--------------------------------------FIM DADOS---------------------------------------
            Button btnPlanejamento = findViewById(R.id.btnPlanejamento);
            Button btnMateria = findViewById(R.id.btnMateria);
            Button btnAtt = findViewById(R.id.btnAtualizar);

            btnAtt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rvAtt();
                }
            });
            tAdapter = new TableAdapter(matriz);

            tAdapter.setListener(new TableAdapter.OnPalavraClickListener() {
                @Override
                public void onPalavraClick(View v, int position) {
                    Intent intent = new Intent(PlanejamentosActivity.this, DisciplinasCursadasActivity.class);
                    String[] temp = new ArrayList<>().toArray(new String[6]);
                    ArrayList<String> tempMatriz = new ArrayList<>();
                    tempMatriz = matriz.get(position);
                    ArrayList<String[]> materia =new ArrayList<>();
                    for (int i=0; i<materias.size(); i++){
                        temp = materias.get(i);
                        if(temp[3].equals(tempMatriz.get(0)) && temp[4].equals(tempMatriz.get(1))){
                            materia.add(temp);
                        }
                    }
                    intent.putExtra("materia", materia);
                    startActivity(intent);
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
                    planejamento = convertePorcentagem(planejamento);
                    this.matriz.add(planejamento);
                    rvAtt();
                }
                else if (requestCode == PlanejamentosActivity.REQUEST_MATERIA && resultCode == PlanejamentosActivity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    String[] materia;
                    materia = (String[]) bundle.get("materia");
                    this.materias.add(materia);
                    rvAtt();
                }
            }
        }
    }
