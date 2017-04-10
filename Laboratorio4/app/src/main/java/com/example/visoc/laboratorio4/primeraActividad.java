package com.example.visoc.laboratorio4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class primeraActividad extends AppCompatActivity {
    private EditText etuser,etnum;//EditText del usuario EditText del numero
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_actividad);
        etuser= (EditText)findViewById(R.id.usuario);
        etnum= (EditText)findViewById(R.id.contra);

    }
    public void enviar_datos(View view){
        Intent intent =new Intent(this,segundaActividad.class);
        intent.putExtra("user_name",etuser.getText().toString());
        intent.putExtra("user_num",etnum.getText().toString());
        startActivity(intent);
        finish();

    }

}

