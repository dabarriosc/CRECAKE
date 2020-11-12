package com.example.creativecake;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorProductosCarrito  extends RecyclerView.Adapter<AdaptadorProductosCarrito.ViewHolderDatosCarrito> {
    ArrayList<p_ejemplo_carrito> listaProductos;

    public AdaptadorProductosCarrito(ArrayList<p_ejemplo_carrito> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public ViewHolderDatosCarrito onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.p_ejemplo_carrito,null,false);
        return new ViewHolderDatosCarrito(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatosCarrito holder, int position) {
        //holder.imagenProducto.setImageResource(listaProductos.get(position).getImagenProducto());
        holder.nombreProducto.setText(listaProductos.get(position).getNombreProducto());
        holder.valorProducto.setText(listaProductos.get(position).getValorProducto());
        holder.totalItems.setText(listaProductos.get(position).getTotalItems());
    }


    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolderDatosCarrito extends RecyclerView.ViewHolder {
        ImageView imagenProducto;
        TextView nombreProducto, valorProducto, totalItems;

        public ViewHolderDatosCarrito(@NonNull View itemView) {
            super(itemView);
            imagenProducto= (ImageView) itemView.findViewById(R.id.imagen_producto);
            nombreProducto= (TextView) itemView.findViewById(R.id.nombre_producto);
            valorProducto= (TextView) itemView.findViewById(R.id.precio_producto);
            totalItems= (TextView) itemView.findViewById(R.id.total_item);
        }
    }
}
