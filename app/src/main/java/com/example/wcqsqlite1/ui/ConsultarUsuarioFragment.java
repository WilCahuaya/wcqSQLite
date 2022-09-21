package com.example.wcqsqlite1.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wcqsqlite1.Entidades.SqliteUtil;
import com.example.wcqsqlite1.R;

import Utilidades.UtilWCQ;

public class ConsultarUsuarioFragment extends Fragment {
    EditText edtConsulIdUsuario;
    TextView txtNombre, txtTelefono;
    Button btnBuscar;
    SqliteUtil conexion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_consultar_usuario, container, false);
        edtConsulIdUsuario= vista.findViewById(R.id.edtConsulIdUsuario);
        txtNombre= vista.findViewById(R.id.txtNombre);
        txtTelefono= vista.findViewById(R.id.txtTelefono);
        btnBuscar= vista.findViewById(R.id.btnBuscar);
        conexion= new SqliteUtil(getContext(), UtilWCQ.BASE_DATOS, null,1);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarUsuario();
            }
        });
        return vista;
    }
    private void consultarUsuario() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        //no mesclar SQL con logica de negocio
        String[] parametros={edtConsulIdUsuario.getText().toString()};
        String[] lista_de_campos={UtilWCQ.CAMPO_ID,UtilWCQ.CAMPO_NOMBRE,UtilWCQ.CAMPO_TELEFONO};
        Toast.makeText(getContext(), ""+parametros, Toast.LENGTH_SHORT).show();
        //captura en caso de error
        try {
            Cursor cursor=db.query(UtilWCQ.TABLA_USUARIO,lista_de_campos,UtilWCQ.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            txtNombre.setText(cursor.getString(1));
            txtTelefono.setText(cursor.getString(2));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getContext(), "Error al buscar Id USUARIO"+edtConsulIdUsuario.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}