package br.ufjf.dcc196.trabalho01;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DisciplinasAdapter extends RecyclerView.Adapter<DisciplinasAdapter.ViewHolder> {

    private ArrayList<Disciplinas> disciplinas;

    private OnDisciplinasClickListener listener;

    public DisciplinasAdapter(ArrayList<Disciplinas> disciplinas) { this.disciplinas = disciplinas; }

    public void setListener(DisciplinasAdapter.OnDisciplinasClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linha = inflater.inflate(R.layout.table2_layout,parent,false);
        DisciplinasAdapter.ViewHolder vh = new DisciplinasAdapter.ViewHolder(linha);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMateria.setText(String.valueOf(disciplinas.get(position).getNome()));
        holder.txtTotalHoras.setText(String.valueOf(disciplinas.get(position).getThoras()) + "h");
        holder.txtArea.setText(String.valueOf(disciplinas.get(position).getArea()));
    }

    @Override
    public int getItemCount() {
        return this.disciplinas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtMateria;
        public TextView txtTotalHoras;
        public TextView txtArea;

        public ViewHolder(View itemView) {
            super(itemView);
            txtMateria = itemView.findViewById(R.id.txtMateria);
            txtTotalHoras = itemView.findViewById(R.id.txtTHoras);
            txtArea = itemView.findViewById(R.id.txtArea);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDisciplinasClick(v, position);
                        }

                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){
                listener.onDisciplinasClick(v, position);
            }
        }
    }

    public interface OnDisciplinasClickListener {
        public void onDisciplinasClick(View v, int position);
    }
}
