package com.example.quizifmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quizifmobile.R;

import java.util.List;

import ModelDominio.Jogo;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.MyViewHolder> {

    private List<Jogo> listaJogo;

    public RankingAdapter(List<Jogo> listaJogo) {
        this.listaJogo = listaJogo;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rankingcardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RankingAdapter.MyViewHolder holder, int position, final List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return listaJogo.size();
    }

    @Override
    public void onBindViewHolder(final RankingAdapter.MyViewHolder holder, final int position) {

        Jogo meuJogo = listaJogo.get(position);
        holder.tvRankingCardView_Colocacao.setText(String.valueOf(position+1));
        holder.tvRankingCardView_Jogador.setText(meuJogo.getJogador().getNomeUsuario());
        holder.tvRankingCardView_NumPer.setText(String.valueOf(meuJogo.getNumPerguntas()));
        holder.tvRankingCardView_NumAcerto.setText(String.valueOf(meuJogo.getNumAcertos()));
        holder.tvRankingCardView_Media.setText(String.valueOf(meuJogo.getPontuacao()) + "%");

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvRankingCardView_Colocacao, tvRankingCardView_Jogador, tvRankingCardView_NumPer, tvRankingCardView_NumAcerto,tvRankingCardView_Media;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvRankingCardView_Colocacao= itemView.findViewById(R.id.tvRankingCardView_Colocacao);
            tvRankingCardView_Jogador = itemView.findViewById(R.id.tvRankingCardView_Jogador);
            tvRankingCardView_NumPer = itemView.findViewById(R.id.tvRankingCardView_NumPer);
            tvRankingCardView_NumAcerto = itemView.findViewById(R.id.tvRankingCardView_NumAcerto);
            tvRankingCardView_Media = itemView.findViewById(R.id.tvRankingCardView_Media);
        }
    }

}