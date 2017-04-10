package com.example.visoc.primeraaplicacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class primeraAplicacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_aplicacion);
    }

    public void onClick(View view){
        Intent intent = new Intent(this, SegundaActividad.class);
        startActivity(intent);
        finish();
    }
}
