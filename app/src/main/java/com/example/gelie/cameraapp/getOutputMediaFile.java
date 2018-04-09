package com.example.gelie.cameraapp;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class getOutputMediaFile {

    public static final int MEDIA_TYPE_IMAGE = 1;

    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CameraApp");

        if (! mediaStorageDir.exists()) {
            if (! mediaStorageDir.mkdirs()) {
                Log.d("CameraApp", "failed to create directory");
                return null;
            }
        }

        return mediaStorageDir;
    }

    String timeStamp =


}
