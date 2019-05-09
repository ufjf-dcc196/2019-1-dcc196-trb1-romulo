package br.ufjf.dcc196.trabalho01;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanejamentosAdapter extends RecyclerView.Adapter<PlanejamentosAdapter.ViewHolder> {

    private ArrayList<Planejamentos> p;

    private OnPlanejamentoClickListener listener;

    public PlanejamentosAdapter(ArrayList<Planejamentos> planejamentos) {
        this.p = planejamentos;
    }

    public void setListener(OnPlanejamentoClickListener listener){this.listener = listener; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linha = inflater.inflate(R.layout.table_layout, parent, false);
        ViewHolder vh = new ViewHolder(linha);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtAno.setText(String.valueOf(p.get(position).getAno()));
        holder.txtSemestre.setText(String.valueOf(p.get(position).getSemestre()));
        holder.txtTotalHoras.setText(String.valueOf(p.get(position).getHoras()) + "h");
        holder.txtLinguas.setText(String.valueOf(p.get(position).getHorasLinguas()) + "h");
        holder.txtHumanas.setText(String.valueOf(p.get(position).getHorasHumanas()) + "h");
        holder.txtExatas.setText(String.valueOf(p.get(position).getHorasExatas()) + "h");
        holder.txtSaude.setText(String.valueOf(p.get(position).getHorasSaude()) + "h");
    }

    @Override
    public int getItemCount() {
        return this.p.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtAno;
        public TextView txtSemestre;
        public TextView txtTotalHoras;
        public TextView txtLinguas;
        public TextView txtHumanas;
        public TextView txtExatas;
        public TextView txtSaude;

        public ViewHolder(View itemView) {
            super(itemView);
            txtAno = itemView.findViewById(R.id.txtAno);
            txtSemestre = itemView.findViewById(R.id.txtSemestre);
            txtTotalHoras = itemView.findViewById(R.id.txtTotalHoras);
            txtLinguas = itemView.findViewById(R.id.txtLingua);
            txtHumanas = itemView.findViewById(R.id.txtHumanas);
            txtExatas = itemView.findViewById(R.id.txtExatas);
            txtSaude = itemView.findViewById(R.id.txtSaude);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onPlanejamentoClick(v, position);
                        }

                    }
                }
            });
            }
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    listener.onPlanejamentoClick(v, position);
                }
            }
    }
    public interface OnPlanejamentoClickListener {
        public void onPlanejamentoClick(View v, int position);
    }

}
