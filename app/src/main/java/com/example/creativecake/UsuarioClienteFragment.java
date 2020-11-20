package com.example.creativecake;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;
import java.util.Map;


public class UsuarioClienteFragment extends Fragment {

    EditText etNombre, etCorreo, etPassword, etDireccion, etEdad;
    TextView tvNombreGrande,edTelefono;
    Button modificar;
    private DatabaseReference reference;
    private Context globalContext = null;

    public UsuarioClienteFragment() {
        // Required empty public constructor
    }

    public static UsuarioClienteFragment newInstance(String param1, String param2) {
        UsuarioClienteFragment fragment = new UsuarioClienteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
        globalContext = this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usuario_cliente, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent1 = getActivity().getIntent();
        final String telefono = intent1.getStringExtra("telefono");

        tvNombreGrande = (TextView) view.findViewById(R.id.idNombrePerfilGrande);
        etNombre = (EditText)view.findViewById(R.id.idNombrePerfil);
        etDireccion = (EditText)view.findViewById(R.id.idDireccionPerfil);
        etCorreo = (EditText)view.findViewById(R.id.idCorreoPerfil);
        etPassword = (EditText)view.findViewById(R.id.idPasswordPerfil);
        etEdad = (EditText)view.findViewById(R.id.idEdadUsuario);
        edTelefono = (TextView)view.findViewById(R.id.edTelefono);
        modificar= (Button) view.findViewById(R.id.idBotonModificar);

        reference = FirebaseDatabase.getInstance().getReference().getRoot().child("usuarioCliente").child(telefono);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                UserHelperClass usuario = snapshot.getValue(UserHelperClass.class);
                tvNombreGrande.setText(usuario.getNombre());
                edTelefono.setText(telefono);
                etNombre.setHint(usuario.getNombre());
                etPassword.setHint(usuario.getPassword());
                etCorreo.setHint(usuario.getCorreo());
                etDireccion.setHint(usuario.getDireccion());
                etEdad.setHint(usuario.getEdad());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Map<String,Object> usuarioMap = new HashMap<>();
                    usuarioMap.put("nombre",etNombre.getText().toString());
                    usuarioMap.put("correo", etCorreo.getText().toString());
                    usuarioMap.put("password",etPassword.getText().toString());
                    usuarioMap.put("direccion", etDireccion.getText().toString());
                    usuarioMap.put("edad", etEdad.getText().toString());
                    reference.updateChildren(usuarioMap);

                    Toast.makeText(getActivity(), "Cambios guardados", Toast.LENGTH_SHORT).show();

                } catch(Exception e){
                    Toast.makeText(getActivity(), "Error al actualizar los Datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}