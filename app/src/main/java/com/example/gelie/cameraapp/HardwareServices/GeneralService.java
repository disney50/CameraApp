package com.example.gelie.cameraapp.HardwareServices;

import android.graphics.Bitmap;

public class GeneralService {

    public static Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        return resizedBitmap;
    }

}
