package com.utp.petfriendly.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.utp.petfriendly.R;
import com.utp.petfriendly.model.CampaniasModel;
import com.utp.petfriendly.model.VeterinariaModel;

import java.util.ArrayList;
import java.util.List;

public class CampaniaAdapter extends  RecyclerView.Adapter<CampaniaAdapter.ViewHolder>{

    private Context context;
    private List<CampaniasModel> lista = new ArrayList<>();
    private OnItemClickListener itemClickListener;

    public CampaniaAdapter(Context context, OnItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.campania_row, parent, false);
        return new CampaniaAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(context, lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public interface OnItemClickListener {
        void onItemClick(CampaniasModel item);
    }

    public void setCampania(List<CampaniasModel> lista) {
        this.lista = lista;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDescipcion;
        ImageView imgVet;
        CardView cardView;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txtTitle);
            txtDescipcion = view.findViewById(R.id.txtDescipcion);
            imgVet = view.findViewById(R.id.imgVet);
            cardView = view.findViewById(R.id.cardView);
        }

        public void bind(Context context, CampaniasModel item) {
            txtTitle.setText(item.getTitulo());
            txtDescipcion.setText(item.getDescripcion());
            if (item.getImagen() != null) {
                Picasso.with(context).load(item.getImagen()).into(imgVet);
            }

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }
}
