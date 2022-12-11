package com.utp.petfriendly.view.adapter;

import android.content.Context;
import android.util.Log;
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
import com.utp.petfriendly.model.AdopcionModel;

import java.util.ArrayList;
import java.util.List;

public class AdopcionAdapter extends RecyclerView.Adapter<AdopcionAdapter.ViewHolder> {

    private Context context;
    private List<AdopcionModel> lista = new ArrayList<>();
    private  OnItemClickListener itemClickListener ;

    public AdopcionAdapter(Context context,  OnItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adopcion_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(context, lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public  void  setItemAdopcion(List<AdopcionModel> listaAdopcion){
        this.lista = listaAdopcion;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(AdopcionModel item);
    }


    public AdopcionModel getObject(int position){
        return lista.get(position);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder  {
        TextView txtRaza, txtTamanio,txtRangoEdad,txtEspecialidad;
        ImageView imgPet;
        CardView cardView;

        public ViewHolder(@NonNull View view) {
            super(view);
            imgPet =  view.findViewById(R.id.imgPet);
            txtRaza = view.findViewById(R.id.txtRaza);
            txtTamanio = view.findViewById(R.id.txtTamanio);
            txtRangoEdad = view.findViewById(R.id.txtRangoEdad);
            txtEspecialidad = view.findViewById(R.id.txtEspecialidad);
            cardView = view.findViewById(R.id.cardView);
        }

        public void bind(Context context, AdopcionModel item) {
            txtRaza.setText(item.getRaza());
            txtTamanio.setText(item.getTamanio());
            txtRangoEdad.setText(item.getRangoEdad());
            txtEspecialidad.setText(item.getEspecialidad());
            if(item.getImagen() != null){
               Picasso.with(context).load(item.getImagen()).into(imgPet);
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
