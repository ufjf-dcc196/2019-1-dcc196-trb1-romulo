package br.ufjf.dcc196.trabalho01;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private ArrayList<ArrayList<String>> matriz;

    private OnPalavraClickListener listener;

    public TableAdapter(ArrayList<ArrayList<String>> matriz) {
        this.matriz = matriz;
    }

    public void setListener(OnPalavraClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linha = inflater.inflate(R.layout.table_layout,parent,false);
        ViewHolder vh = new ViewHolder(linha);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArrayList<String> l = matriz.get(position);
        holder.txtAno.setText(String.valueOf(l.get(0)));
        holder.txtSemestre.setText(String.valueOf(l.get(1)));
        holder.txtTotalHoras.setText(String.valueOf(l.get(2)));
        holder.txtLinguas.setText(String.valueOf(l.get(3)));
        holder.txtHumanas.setText(String.valueOf(l.get(4)));
        holder.txtExatas.setText(String.valueOf(l.get(5)));
        holder.txtSaude.setText(String.valueOf(l.get(6)));
    }

    @Override
    public int getItemCount() {
        return matriz.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtAno;
        public TextView txtSemestre;
        public TextView txtTotalHoras;
        public TextView txtLinguas;
        public TextView txtHumanas;
        public TextView txtExatas;
        public TextView txtSaude;

        public ViewHolder(View itemView){
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
                            listener.onPalavraClick(v, position);
                        }

                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){
                listener.onPalavraClick(v, position);
            }
        }
    }

    public interface OnPalavraClickListener {
        public void onPalavraClick(View v, int position);
    }


}
