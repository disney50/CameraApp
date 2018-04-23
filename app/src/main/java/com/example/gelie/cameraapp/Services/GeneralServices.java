package com.example.gelie.cameraapp.Services;

import android.graphics.Bitmap;

public class GeneralServices {

    public static final String TAG = "CameraApp";

    public static Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight) {
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
    }

}
