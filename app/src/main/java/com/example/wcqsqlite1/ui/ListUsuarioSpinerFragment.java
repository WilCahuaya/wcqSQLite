package com.example.wcqsqlite1.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wcqsqlite1.Entidades.SqliteUtil;
import com.example.wcqsqlite1.Entidades.Usuario;
import com.example.wcqsqlite1.R;

import java.util.ArrayList;

import Utilidades.UtilWCQ;

public class ListUsuarioSpinerFragment extends Fragment {
    Spinner spiUsuario;
    TextView txtId,txtNombre,txtTelefono;

    SqliteUtil conexion;

    ArrayList<Usuario> listaUsuarios;
    ArrayList<String> listaInformacio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_list_usuario_spiner, container, false);

        spiUsuario=vista.findViewById(R.id.spiUsuario);
        txtId=vista.findViewById(R.id.txtIdSP);
        txtNombre=vista.findViewById(R.id.txtNombreSP);
        txtTelefono=vista.findViewById(R.id.txtTelefonoSP);

        conexion= new SqliteUtil(getContext(), UtilWCQ.BASE_DATOS,null,1);
        //GENERAR listado de usuario
        generarListarUsuario();
        //ojo formato cada item

        ArrayAdapter<CharSequence> adapter=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,listaInformacio);
        spiUsuario.setAdapter(adapter);
        spiUsuario.getSelectedItemPosition();
        spiUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtId.setText(String.valueOf(listaUsuarios.get(position).getId()));
                txtNombre.setText(String.valueOf(listaUsuarios.get(position).getNombre()));
                txtTelefono.setText(String.valueOf(listaUsuarios.get(position).getTelefono()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return vista;
    }

    private void generarListarUsuario() {
        SQLiteDatabase db=conexion.getReadableDatabase();
        Usuario usuario=null;
        try {
            listaUsuarios= new ArrayList<Usuario>();
            Cursor cursor=db.rawQuery("SELECT * FROM "+UtilWCQ.TABLA_USUARIO,null);
            while(cursor.moveToNext()) {
                usuario = new Usuario();
                usuario.setId((cursor.getString(0)));
                usuario.setNombre((cursor.getString(1)));
                usuario.setTelefono((cursor.getString(2)));
                listaUsuarios.add(usuario);}
            obtenerLista();
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private void obtenerLista(){
        listaInformacio=new ArrayList<String>();
        for (int i=0;i<listaUsuarios.size();i++){
            listaInformacio.add(
                    listaUsuarios.get(i).getId()+" - "+
                            listaUsuarios.get(i).getNombre()+" - "+
                            listaUsuarios.get(i).getTelefono()
            );
        }
    }
}