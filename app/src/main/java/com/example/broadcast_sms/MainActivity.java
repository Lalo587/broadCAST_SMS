package com.example.broadcast_sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtMensaje;
    Context _ctx;
    Button btnGuardarMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMensaje=(EditText)findViewById(R.id.txtMensaje);
        btnGuardarMensaje=(Button)findViewById(R.id.btnGuardarMensaje);
        _ctx=this;
        btnGuardarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ENVIAMOS UN BROADCAST CON EL MENSAJE DE LA CAJA DE TEXTO QUE INGRESO EL USUARIO PARA GUARDARLO EN LA PROXIMA LLAMADA QUE ENTRE.
                Intent intent= new Intent("my.action.string");
                Toast.makeText(_ctx,txtMensaje.getText().toString(),Toast.LENGTH_LONG).show();
                intent.putExtra("Mensaje",txtMensaje.getText().toString());
                sendBroadcast(intent);
            }
        });



    }
}
