package com.example.gelie.cameraapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;
import android.view.View;
import android.widget.Button;
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

        //Test if device has camera(s)
        boolean hasCamera = CameraService.CanIAccessTheCamera(this);
        if (!hasCamera)
        {
          // TODO handle not camera
        }

        //Get an object of camera on device
        mCamera = getCameraInstance();

        //calls the CameraView class that sets up our surfaceview,
        // which in turn is set to the value of mCamView
        mCamView = new CameraView(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview2);
        preview.addView(mCamView);

        //takes picture on button click
        Button captureButton = findViewById(R.id.button_capture);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCamera.takePicture(null, null, mPicture);
                    }
                }
        );
    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

        }
    };



}
