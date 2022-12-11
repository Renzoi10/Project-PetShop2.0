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
import com.utp.petfriendly.model.TiendaModel;

import java.util.ArrayList;
import java.util.List;

public class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.ViewHolder> {

    private Context context;
    private List<TiendaModel> lista = new ArrayList<>();
    private TiendaAdapter.OnItemClickListener itemClickListener;

    public TiendaAdapter(Context context, OnItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tienda_row, parent, false);
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

    public void setItemProduct(List<TiendaModel> lista) {
        this.lista = lista;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(TiendaModel item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameProduct, txtPrice;
        ImageView imgProduct;
        CardView cardView;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtNameProduct = view.findViewById(R.id.txtNameProduct);
            txtPrice = view.findViewById(R.id.txtPrice);
            imgProduct = view.findViewById(R.id.imgProduct);
            cardView = view.findViewById(R.id.cardView);
        }

        public void bind(Context context, TiendaModel item) {
            txtNameProduct.setText(item.getNombreProducto());
            Log.e("PRECIO",""+item.getPrecio());
            txtPrice.setText("S/ "+item.getPrecio());
            if (item.getImagen() != null) {
                Picasso.with(context).load(item.getImagen()).into(imgProduct);
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
