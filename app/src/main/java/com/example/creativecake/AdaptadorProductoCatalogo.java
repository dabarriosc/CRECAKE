package com.example.creativecake;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorProductoCatalogo extends RecyclerView.Adapter<AdaptadorProductoCatalogo.CatalogoviewHolder>{
    ArrayList<producto_ejemplo> listaProductos;

    public AdaptadorProductoCatalogo(ArrayList<producto_ejemplo> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public CatalogoviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.p_ejemplo_cat,parent,false);
        return new CatalogoviewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogoviewHolder holder, int position) {
        producto_ejemplo producto = listaProductos.get(position);
        Picasso.get().load(producto.getDownloadUrl()).placeholder(R.drawable.imagenproducto).into(holder.imagenProducto);
        holder.nombreProducto.setText(producto.getNombre());
        holder.valorProducto.setText(producto.getPrecio());
        holder.pasteleriaProducto.setText(producto.getUser_name());
        holder.tipoProducto.setText(producto.getTipo());
        holder.ofertaProducto.setText(producto.getOferta());
        //holder.ratingProducto.setNumStars(Integer.valueOf(producto.getRating()));
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class  CatalogoviewHolder extends RecyclerView.ViewHolder {

        ImageView imagenProducto;
        TextView nombreProducto, valorProducto, pasteleriaProducto, tipoProducto, ofertaProducto;
        RatingBar ratingProducto;

        public CatalogoviewHolder(@NonNull View itemView) {
            super(itemView);
            imagenProducto= (ImageView) itemView.findViewById(R.id.imagen_producto);
            nombreProducto= (TextView) itemView.findViewById(R.id.nombre_producto);
            valorProducto= (TextView) itemView.findViewById(R.id.precio_producto);
            pasteleriaProducto= (TextView) itemView.findViewById(R.id.pasteleria_producto);
            tipoProducto= (TextView) itemView.findViewById(R.id.tipo_producto);
            ofertaProducto = (TextView) itemView.findViewById(R.id.oferta_producto);
            ratingProducto= (RatingBar) itemView.findViewById(R.id.rating_producto);
        }
    }
}
