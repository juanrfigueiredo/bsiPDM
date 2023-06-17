package com.example.minhacameraapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private Button btTakePicture;
    private FloatingActionButton fabGrayScale;
    private ImageView ivEvandro;
    private static final int REQUEST_CODE_FOTO = 1000;
    private Bitmap thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btTakePicture = findViewById(R.id.btTakePicture);
        fabGrayScale = findViewById(R.id.fabGrayScale);
        ivEvandro = findViewById(R.id.ivEvandro);

        btTakePicture.setOnClickListener(e -> {takePicture();});
        //fabGrayScale.setOnClickListener(e -> {convertToGrayScale();});
        //fabGrayScale.setOnClickListener(e -> {michaelJackson();});
        fabGrayScale.setOnClickListener(e -> {russia();});
    }

    private void convertToGrayScale() {
        Bitmap bitmap = ((BitmapDrawable)ivEvandro.getDrawable()).getBitmap();;
        Bitmap copiabitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        int r,g,b,m,pixel,pixelcinza;
        for(int x=0;x<copiabitmap.getWidth();x++)
            for (int y = 0; y < copiabitmap.getHeight(); y++)
            {
                pixel = copiabitmap.getPixel(x,y);
                r = Color.red(pixel);
                g = Color.green(pixel);
                b = Color.blue(pixel);
                m = (int)(0.2989 * r + 0.5870 * g + 0.1140 * b);
                pixelcinza=Color.rgb(m,m,m);
                copiabitmap.setPixel(x,y,pixelcinza);
            }
        // insere a cópia transformada no imageview
        ivEvandro.setImageBitmap(copiabitmap);
    }

    private void michaelJackson() {
        Bitmap bitmap = ((BitmapDrawable)ivEvandro.getDrawable()).getBitmap();;
        Bitmap copiabitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        int r,g,b,m,pixel,pixelcinza;
        for(int x=0;x<copiabitmap.getWidth();x++)
            for (int y = 0; y < copiabitmap.getHeight(); y++)
            {
                pixel = copiabitmap.getPixel(x,y);
                r = Color.red(pixel);
                g = Color.green(pixel);
                b = Color.blue(pixel);
                m = (int)(0.2989 * r + 0.5870 * g + 0.1140 * b);

                if(m>130)
                    pixel=Color.rgb(255,255,255);
                else
                    pixel=Color.rgb(0,0,0);

                copiabitmap.setPixel(x,y,pixel);

            }
        // insere a cópia transformada no imageview
        ivEvandro.setImageBitmap(copiabitmap);
    }

    private void russia() {
        Bitmap bitmap = ((BitmapDrawable)ivEvandro.getDrawable()).getBitmap();;
        Bitmap copiabitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        int r,g,b,m,pixel,pixelcinza;
        for(int x=0;x<copiabitmap.getWidth();x++)
            for (int y = 0; y < copiabitmap.getHeight(); y++)
            {
                pixel = copiabitmap.getPixel(x,y);
                r = Color.red(pixel);
                g = Color.green(pixel);
                b = Color.blue(pixel);
                pixel = Color.rgb(255-r,255-g,255-b);
                copiabitmap.setPixel(x,y,pixel);

            }
        // insere a cópia transformada no imageview
        ivEvandro.setImageBitmap(copiabitmap);
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, REQUEST_CODE_FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Fechou a tela da câmera
        if(requestCode == REQUEST_CODE_FOTO) {
            // Confirmou a foto
            if (resultCode == Activity.RESULT_OK) {
                thumbnail = data.getParcelableExtra("data");
                ivEvandro.setImageBitmap(thumbnail);
            }
            //cancelou a foto
            else {
                ivEvandro.setImageResource(R.mipmap.ic_launcher_round);
            }
        }
    }

}

