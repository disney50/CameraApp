package com.example.gelie.cameraapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;
import android.widget.FrameLayout;


import com.example.gelie.cameraapp.HardwareServices.CameraService;

import static com.example.gelie.cameraapp.HardwareServices.CameraService.getCameraInstance;

public class MainActivity extends AppCompatActivity {

    static Camera mCamera = null;
    private CameraView mCamView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean hasCamera = CameraService.CanIAccessTheCamera(this);
        if (!hasCamera)
        {
          // TODO handle not camera
        }


        mCamera = getCameraInstance();

        mCamView = new CameraView(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview2);
        preview.addView(mCamView);


    }



}
