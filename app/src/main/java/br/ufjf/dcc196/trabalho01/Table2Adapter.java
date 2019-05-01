package br.ufjf.dcc196.trabalho01;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Table2Adapter extends RecyclerView.Adapter<Table2Adapter.ViewHolder> {

    private ArrayList<String[]> materias;

    private Table2Adapter.OnPalavra2ClickListener listener;

    public Table2Adapter(ArrayList<String[]> materias) {
        this.materias = materias;
    }

    public void setListener(Table2Adapter.OnPalavra2ClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Table2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linha = inflater.inflate(R.layout.table2_layout,parent,false);
        Table2Adapter.ViewHolder vh = new Table2Adapter.ViewHolder(linha);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Table2Adapter.ViewHolder holder, int position) {

        //for(int i=0; i<materias.size(); i++) {

            String[] l = materias.get(position);

            holder.txtMateria.setText(String.valueOf(l[0]));
            holder.txtTotalHoras.setText(String.valueOf(l[1]));
            holder.txtArea.setText(String.valueOf(l[2]));
        //}

        /*ArrayList<String> l = materias.get(position);
        holder.txtMateria.setText(String.valueOf(l.get(0)));
        holder.txtArea.setText(String.valueOf(l.get(0)));
        holder.txtTotalHoras.setText(String.valueOf(l.get(0)));*/
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtMateria;
        public TextView txtTotalHoras;
        public TextView txtArea;

        public ViewHolder(View itemView){
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
                            listener.onPalavra2Click(v, position);
                        }

                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){
                listener.onPalavra2Click(v, position);
            }
        }
    }

    public interface OnPalavra2ClickListener {
        public void onPalavra2Click(View v, int position);
    }

}
