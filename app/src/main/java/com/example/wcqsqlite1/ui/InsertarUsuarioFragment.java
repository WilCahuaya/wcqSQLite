package com.example.wcqsqlite1.ui;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wcqsqlite1.Entidades.SqliteUtil;
import com.example.wcqsqlite1.R;

import Utilidades.UtilWCQ;

public class InsertarUsuarioFragment extends Fragment {
    EditText edtCodigo, edtNombre,edtTelefono;
    Button btnRegistrarUsuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_insertar_usuario, container, false);
        edtCodigo=vista.findViewById(R.id.edtIdUsuario);
        edtNombre=vista.findViewById(R.id.edtNombreUsuario);
        edtTelefono=vista.findViewById(R.id.edtPhoneUser);
        btnRegistrarUsuario=vista.findViewById(R.id.btnRegistrarUsuario);
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regUsuario();
            }
        });
        return vista;
    }
    public void regUsuario(){
        SqliteUtil conexion=new SqliteUtil(getContext(),"bd_usuario",null,1);
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put(UtilWCQ.CAMPO_ID, edtCodigo.getText().toString());
        valores.put(UtilWCQ.CAMPO_NOMBRE, edtNombre.getText().toString());
        valores.put(UtilWCQ.CAMPO_TELEFONO, edtTelefono.getText().toString());

        Long resultado=db.insert(UtilWCQ.TABLA_USUARIO,null,valores);
        Toast.makeText(getContext(),"Registro completo",Toast.LENGTH_SHORT).show();
        db.close();

    }
}