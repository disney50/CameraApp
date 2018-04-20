package com.example.gelie.cameraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.gelie.cameraapp.HardwareServices.StorageService;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<String> images;
    File[] listFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        images = new ArrayList<>();
        getFromSdCard();

        gridView = findViewById(R.id.grid_gallery);
        gridView.setAdapter(new ImageAdapter(this, images));
    }

    private void getFromSdCard() {
        File file = StorageService.GetCameraAppDir();

        if (file.isDirectory()) {
            listFile = file.listFiles();

            for (File aListFile : listFile) {
                images.add(aListFile.getAbsolutePath());
            }
        }
    }
}