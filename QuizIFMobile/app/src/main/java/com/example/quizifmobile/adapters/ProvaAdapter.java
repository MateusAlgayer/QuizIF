package com.example.quizifmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quizifmobile.R;
import ModelDominio.Prova;

import java.util.List;

public class ProvaAdapter extends RecyclerView.Adapter<ProvaAdapter.MyViewHolder> {
    private List<Prova> listaProvas;
    private ProvaOnClickListener provaOnClickListener;

    public ProvaAdapter(List<Prova> listaProvas, ProvaOnClickListener provaOnClickListener) {
        this.listaProvas = listaProvas;
        this.provaOnClickListener = provaOnClickListener;
    }

    @Override
    public ProvaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.provacardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProvaAdapter.MyViewHolder holder, int position, final List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return listaProvas.size();
    }

    public Prova getProva(int position){
        return listaProvas.get(position);
    }

    @Override
    public void onBindViewHolder(final ProvaAdapter.MyViewHolder holder, final int position) {
        Prova minhaProva = listaProvas.get(position);
        holder.tvProvaCardView_Prova.setText(minhaProva.getNomeProva());
        holder.tvProvaCardView_Area.setText(minhaProva.getAreaGeral().getNome());
        holder.tvProvaCardView_Dificuldade.setText(minhaProva.getDificuldadeLiteral());
        holder.tvProvaCardView_Pontuacao.setText(String.valueOf(minhaProva.getPontuacao()));

        if (provaOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    provaOnClickListener.onClickProva(holder.itemView,position);
                }
            });
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvProvaCardView_Prova, tvProvaCardView_Area, tvProvaCardView_Dificuldade, tvProvaCardView_Pontuacao;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvProvaCardView_Prova = itemView.findViewById(R.id.tvProvaCardView_Prova);
            tvProvaCardView_Area  = itemView.findViewById(R.id.tvProvaCardView_Area);
            tvProvaCardView_Dificuldade  = itemView.findViewById(R.id.tvProvaCardView_Dificuldade);
            tvProvaCardView_Pontuacao  = itemView.findViewById(R.id.tvProvaCardView_Pontuacao);
        }
    }

    public interface ProvaOnClickListener {
        public void onClickProva(View view, int position);
    }
}
