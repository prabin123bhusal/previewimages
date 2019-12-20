package com.c.previewimages;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;

import android.widget.Button;

import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    Button btnSave;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        btnSave = findViewById(R.id.btnSave);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

            }

        });

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

            }

        });

    }

}

private void BrowseImage(){
    Intent intent=new Intent(Intent.ACTION_PICK);
    intent.setType("image/*");
    startActivityForResult(intent, 0);
}

    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){

            if (data == null){

                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();

            }

        }

        Uri uri = data.getData();

        imageView.setImageURI(uri);

        String imagePath = getRealPathFromUri(uri);

// previewImage(imagePath);

    }

    private String getRealPathFromUri(Uri uri){

        String[] projection = {MediaStore.Images.Media.DATA};

        CursorLoader loader = new CursorLoader(getApplicationContext(),uri,projection,null,null,null);

        Cursor cursor = loader.loadInBackground();

        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String result = cursor.getString(colIndex);

        cursor.close();

        return result;

    }

    private void previewImage(String imagePath){

        File imgFile = new File(imagePath);

        if (imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            imageView.setImageBitmap(myBitmap);

        }

    }

}



