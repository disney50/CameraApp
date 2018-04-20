package com.example.gelie.cameraapp;


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
import android.widget.ImageView;


import com.example.gelie.cameraapp.HardwareServices.CameraService;

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
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final String TAG = "CameraApp";
    private ImageView mImage;

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
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mCamView);

        mImage = findViewById(R.id.image_capture);

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

    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "CameraApp");

        if (! mediaStorageDir.exists()) {
            if (! mediaStorageDir.mkdirs()) {
                Log.d(TAG, "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG" + timeStamp + ".jpg");
        }
        else {
            return null;
        }

        return mediaFile;
    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null) {
                Log.d(TAG, "Error creating media file, check storage permissions" /*+ e.getMessage()*/);
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

            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if(bitmap == null){
                Log.d(TAG, "Captured image is empty");
                return;
            }

            mImage.setImageBitmap(scaleDownBitmapImage(bitmap, 85, 85 ));

            mCamView.restartCameraView();
        }
    };

    private Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        return resizedBitmap;
    }

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
