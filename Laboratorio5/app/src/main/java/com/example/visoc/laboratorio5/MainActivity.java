package com.example.visoc.laboratorio5;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    String URL = "https://static1.comicvine.com/uploads/original/11124/111241743/5266144-5398474343-monke.png";
    ImageView image;
    Button descarga;
    Button barra;
    private static final String NAME_FILE="LUFFY.png";
    private ProgressDialog Progress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.imageView);
        descarga = (Button) findViewById(R.id.descarga);
        barra =(Button)findViewById(R.id.barra);

        descarga.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                new Descargar().execute(URL);
            }
        });

        barra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Progress = new ProgressDialog(MainActivity.this);
                Progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                Progress.setMessage("Procesando...");
                Progress.setCancelable(true);
                Progress.setMax(10);
                new Barra().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);



            }
        });
    }
    private void tareaLarga()
    {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ignored) {}
    }



    private class Barra extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            for(int i=1; i<=10; i++) {
                tareaLarga();

                publishProgress(i);

                if(isCancelled())
                    break;
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0];

            Progress.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {

            Progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Barra.this.cancel(true);
                }
            });

            Progress.setProgress(0);
            Progress.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                Progress.dismiss();
                Toast.makeText(MainActivity.this, "Tarea finalizada!",
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this, "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }



    private class Descargar extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();

                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView
            image.setImageBitmap(result);

        }
    }





}