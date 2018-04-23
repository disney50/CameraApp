package com.example.gelie.cameraapp.HardwareServices;

import android.os.Environment;
import android.util.Log;

import com.example.gelie.cameraapp.MainActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StorageService {

    public static File GetCameraAppDir() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "CameraApp");
    }

    public static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "CameraApp");

        if (! mediaStorageDir.exists()) {
            if (! mediaStorageDir.mkdirs()) {
                Log.d(MainActivity.TAG, "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator + "IMG" + timeStamp + ".jpg");
    }
}
