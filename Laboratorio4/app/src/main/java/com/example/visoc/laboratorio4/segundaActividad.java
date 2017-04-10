package com.example.visoc.laboratorio4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class segundaActividad extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE =0;
    private TextView tvName,tvNum;
    String numero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);
        tvName=(TextView)findViewById(R.id.Mensaje);
        Bundle b=(Bundle)getIntent().getExtras();
        String nombre=(String)b.get("user_name");
        numero=(String)b.get("user_num");
        tvName.setText(nombre+"\n" +numero);
    }
    public void llamar(View view) {
        if (ContextCompat.checkSelfPermission(segundaActividad.this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {//verifica si existe el permiso otorgado por el usuario

            ActivityCompat.requestPermissions(segundaActividad.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);//solicita el permison

        }



        if(ContextCompat.checkSelfPermission(segundaActividad.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_DENIED) {//si el permiso existe realiza la llamada
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+numero));//realiza la llamada tel nos permite poder realizar la llamada
            startActivity(intent);
        }


    }
}
