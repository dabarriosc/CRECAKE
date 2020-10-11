package com.example.creativecake;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_inventario_nuevoproducto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_inventario_nuevoproducto extends Fragment {

    FirebaseDatabase database;
    DatabaseReference reference;


    private EditText nombre;
    private EditText precio;
    private EditText descripcion;
    private Button agregar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_inventario_nuevoproducto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_inventario_nuevoproducto.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_inventario_nuevoproducto newInstance(String param1, String param2) {
        fragment_inventario_nuevoproducto fragment = new fragment_inventario_nuevoproducto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventario_nuevoproducto, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        nombre = (EditText) view.findViewById(R.id.editNombrenuevoproducto);
        precio = (EditText) view.findViewById(R.id.editPrecionuevoproducto);
        descripcion = (EditText) view.findViewById(R.id.editDescripcionnuevoproducto);
        agregar = (Button) view.findViewById(R.id.buttonAgregarnuevoproducto);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("producto");

                String nombreProducto = nombre.getText().toString();
                String precioProducto = precio.getText().toString();
                String descripcionProducto = descripcion.getText().toString();
                ProductHelperClass helperClass = new ProductHelperClass(nombreProducto, precioProducto
                        , descripcionProducto, nombreProducto);


                reference.child(nombreProducto).setValue(helperClass);

            }
        });
    }
}