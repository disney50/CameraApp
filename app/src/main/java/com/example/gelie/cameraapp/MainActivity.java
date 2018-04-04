package com.example.gelie.cameraapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;

import com.example.gelie.cameraapp.HardwareServices.CameraService;

public class MainActivity extends AppCompatActivity {
    static Camera camera = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean hasCamera = CameraService.CanIAccessTheCamera(this);

        if (!hasCamera) {
          // TODO handle not camera
        }


        Camera appCam;
        appCam = CameraService.getCameraInstance();

    }




}
