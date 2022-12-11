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
import com.utp.petfriendly.model.VeterinariaModel;

import java.util.ArrayList;
import java.util.List;

public class VeterinariaAdapter extends RecyclerView.Adapter<VeterinariaAdapter.ViewHolder> {

    private Context context;
    private List<VeterinariaModel> lista = new ArrayList<>();
    private VeterinariaAdapter.OnItemClickListener itemClickListener;

    public VeterinariaAdapter(Context context, VeterinariaAdapter.OnItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public VeterinariaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.veterinaria_row, parent, false);
        return new VeterinariaAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VeterinariaAdapter.ViewHolder holder, int position) {
        holder.bind(context, lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setItemProduct(List<VeterinariaModel> lista) {
        this.lista = lista;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(VeterinariaModel item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVeterinaria, txtAtencion, txtTelefono, txtdireccion;
        ImageView imgVet;
        CardView cardView;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtVeterinaria = view.findViewById(R.id.txtVeterinaria);
            txtAtencion = view.findViewById(R.id.txtAtencion);
            txtTelefono = view.findViewById(R.id.txtTelefono);
            imgVet = view.findViewById(R.id.imgVet);
            cardView = view.findViewById(R.id.cardView);
            txtdireccion= view.findViewById(R.id.txtdireccion);
        }

        public void bind(Context context, VeterinariaModel item) {
            txtVeterinaria.setText(item.getNombreVeterinaria());
            txtAtencion.setText(item.getAtencion());
            txtTelefono.setText(item.getTelefono());
            txtdireccion.setText(item.getDireccion());
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
