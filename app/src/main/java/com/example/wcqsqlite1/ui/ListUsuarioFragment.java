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
import android.widget.ListView;
import android.widget.Toast;

import com.example.wcqsqlite1.Entidades.SqliteUtil;
import com.example.wcqsqlite1.Entidades.Usuario;
import com.example.wcqsqlite1.R;

import java.util.ArrayList;

import Utilidades.UtilWCQ;

public class ListUsuarioFragment extends Fragment {
    ListView lstUsuario;
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



        View vista=inflater.inflate(R.layout.fragment_list_usuario, container, false);
        lstUsuario=vista.findViewById(R.id.lstUsuariosSimple);
        conexion= new SqliteUtil(getContext(), UtilWCQ.BASE_DATOS, null,1);
        //GENERAR listado de usuario
        generarListarUsuario();

        ArrayAdapter adapter=new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,listaInformacio);
        lstUsuario.setAdapter(adapter);
        lstUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                String info=listaUsuarios.get(posicion).toString();
                Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
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