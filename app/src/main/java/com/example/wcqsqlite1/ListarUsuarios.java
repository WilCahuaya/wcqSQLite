package com.example.wcqsqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wcqsqlite1.Entidades.SqliteUtil;
import com.example.wcqsqlite1.Entidades.Usuario;

import java.util.ArrayList;

import Utilidades.UtilWCQ;

public class ListarUsuarios extends AppCompatActivity {
    ListView lstUsuario;
    SqliteUtil conexion;
    ArrayList<Usuario> listaUsuarios;
    ArrayList<String> listaInformacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
        conexion= new SqliteUtil(this, UtilWCQ.BASE_DATOS, null,1);
        //GENERAR listado de usuario
        generarListarUsuario();

        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacio);
        lstUsuario.setAdapter(adapter);
        lstUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                String info=listaUsuarios.get(posicion).toString();
                Toast.makeText(ListarUsuarios.this, info, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generarListarUsuario() {
        SQLiteDatabase db=conexion.getReadableDatabase();
        Usuario usuario=null;
        try {
            listaUsuarios= new ArrayList<Usuario>();
            Cursor curso=db.rawQuery("select * from "+UtilWCQ.TABLA_USUARIO,null);
            while(curso.moveToNext()) {
                usuario = new Usuario();
                usuario.setId((curso.getString(0)));
                usuario.setNombre((curso.getString(1)));
                usuario.setTelefono((curso.getString(2)));
                listaUsuarios.add(usuario);}
                obtenerLista();
            }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
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


//lstUsuario=findViewById(R.id.lstUsuariosSimple);
//        //se requiere de un adaptador
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.item_lista,R.id.txtNombreNave,navesNasa);
//        lstUsuario.setAdapter(adapter);
//        lstUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//@Override
//public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
//        Toast.makeText(ListarUsuarios.this, navesNasa[posicion], Toast.LENGTH_SHORT).show();
//        }
//        });