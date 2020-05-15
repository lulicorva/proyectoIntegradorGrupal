package com.example.proyectointegradorgrupal.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectointegradorgrupal.R;
import com.example.proyectointegradorgrupal.model.Recomendados;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecomendadosAdapter extends RecyclerView.Adapter<RecomendadosAdapter.ViewHolderRecomendados> {

    private List<Recomendados> recomendadosList;

    public RecomendadosAdapter(List<Recomendados> recomendadosList) {
        this.recomendadosList = recomendadosList;
    }

    @NonNull
    @Override
    public ViewHolderRecomendados onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ceda_recomendados,parent,false);

        return new ViewHolderRecomendados(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecomendados holder, int position) {
        Recomendados recomendados = this.recomendadosList.get(position);
        holder.onBind(recomendados);

    }

    @Override
    public int getItemCount() { return recomendadosList.size(); }

    protected class ViewHolderRecomendados extends RecyclerView.ViewHolder {

        private CircleImageView circleImageViewRecomendados;
        private MaterialTextView materialTextViewNombreRecomendados;

        private ViewHolderRecomendados(@NonNull View itemView) {

            super(itemView);
            circleImageViewRecomendados = itemView.findViewById(R.id.recomendadosImageView);
            materialTextViewNombreRecomendados = itemView.findViewById(R.id.recomendadosTextView);


        }

        public void onBind(Recomendados recomendados) {
            circleImageViewRecomendados.setImageResource(recomendados.getImagen());
            materialTextViewNombreRecomendados.setText(recomendados.getNombre());

        }
    }

}
