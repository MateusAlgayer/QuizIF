package com.example.quizifmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizifmobile.R;

import java.util.HashMap;
import java.util.List;

import ModelDominio.Pergunta;

public class ResultadoAdapter extends RecyclerView.Adapter<ResultadoAdapter.MyViewHolder> {
    private List<Pergunta> listaPerguntas;
    private  HashMap<Integer, Boolean> respostas;

    public ResultadoAdapter(List<Pergunta> listaProvas, HashMap<Integer, Boolean> respostas) {
        this.listaPerguntas = listaProvas;
        this.respostas = respostas;
    }

    @Override
    public ResultadoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resultadocardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ResultadoAdapter.MyViewHolder holder, int position, final List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return listaPerguntas.size();
    }

    @Override
    public void onBindViewHolder(final ResultadoAdapter.MyViewHolder holder, final int position) {
        Pergunta pergunta = listaPerguntas.get(position);
        holder.tvResultadoPergunta.setText(pergunta.getPergunta());

        holder.cardViewResultado.setBackgroundResource(respostas.get(position) ? R.color.green : R.color.red);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvResultadoPergunta;
        CardView cardViewResultado;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvResultadoPergunta = itemView.findViewById(R.id.tvResultadoPergunta);
            cardViewResultado  = itemView.findViewById(R.id.CardViewResultado);
        }
    }
}
