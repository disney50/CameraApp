package com.example.gelie.cameraapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;

import com.example.gelie.cameraapp.HardwareServices.CameraService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //boolean hasCamera = CameraService.CanIAccessTheCamera(this);

      //if (!hasCamera) {
        //  // TODO handle not camera
          // return;
       //}

        getCameraInstance();

    }

    public static Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        }
        catch (Exception exception) {

        }
        return camera;

    }


}
