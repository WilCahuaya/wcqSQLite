package com.example.wcqsqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wcqsqlite1.Entidades.SqliteUtil;

import Utilidades.UtilWCQ;

public class InsertarUsuario extends AppCompatActivity {
EditText edtCodigo, edtNombre,edtTelefono;
Button btnRegistrarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_usuario);

        //public static final String TABLA_USUARIO="usuario";
        //    public static final String CAMPO_ID="id";
        //    public static final String CAMPO_NOMBRE="nombre";
        //    public static final String CAMPO_TELEFONO="telefono";

        edtCodigo=findViewById(R.id.edtIdUsuario);
        edtNombre=findViewById(R.id.edtNombreUsuario);
        edtTelefono=findViewById(R.id.edtPhoneUser);
        btnRegistrarUsuario=findViewById(R.id.btnRegistrarUsuario);
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regUsuario();
            }
        });
    }
    public void regUsuario(){
        SqliteUtil conexion=new SqliteUtil(this,"bd_usuario",null,1);
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put(UtilWCQ.CAMPO_ID, edtCodigo.getText().toString());
        valores.put(UtilWCQ.CAMPO_NOMBRE, edtNombre.getText().toString());
        valores.put(UtilWCQ.CAMPO_TELEFONO, edtTelefono.getText().toString());

        Long resultado=db.insert(UtilWCQ.TABLA_USUARIO,null,valores);
        Toast.makeText(this,"Registro completo",Toast.LENGTH_SHORT).show();
        db.close();

    }
}