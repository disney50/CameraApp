package com.example.gelie.cameraapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.example.gelie.cameraapp.HardwareServices.CameraService;
import com.example.gelie.cameraapp.HardwareServices.GeneralService;
import com.example.gelie.cameraapp.HardwareServices.StorageService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.gelie.cameraapp.HardwareServices.CameraService.getCameraInstance;

public class MainActivity extends AppCompatActivity {

    static Camera mCamera = null;
    private CameraView mCamView;
    public static final String TAG = "CameraApp";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Test if device has camera(s)
        boolean hasCamera = CameraService.CanIAccessTheCamera(this);
        if (!hasCamera)
        {
            Log.d(TAG, "Camera access denied");
            return;
        }

        //Get an object of camera on device
        mCamera = getCameraInstance();

        //calls the CameraView class that sets up our surfaceview,
        // which in turn is set to the value of mCamView
        mCamView = new CameraView(this, mCamera);
        FrameLayout preview = findViewById(R.id.camera_preview);
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

        //navigates to galleryactivity on imagebutton click
        ImageButton galleryButton = findViewById(R.id.button_gallery);
        galleryButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mIntent = new Intent(getApplicationContext(), GalleryActivity.class);
                        startActivity(mIntent);
                    }
                }
        );

    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            //stores image via specified path
            File pictureFile = StorageService.getOutputMediaFile();
            if (pictureFile == null) {
                Log.d(TAG, "Error creating media file, check storage permissions");
                return;
            }
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            }

            catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            }

            catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }

            //converts byte array to bitmap which can be displayed in imageview
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if(bitmap == null){
                Log.d(TAG, "Captured image is empty");
                return;
            }

            ImageView imageView = findViewById(R.id.button_gallery);

            imageView.setImageBitmap(GeneralService.scaleDownBitmapImage(bitmap, 85, 85 ));

            mCamView.restartCameraView();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera() {
        if (mCamera == null) {
            mCamera.release();
            mCamera = null;
        }
    }
}
