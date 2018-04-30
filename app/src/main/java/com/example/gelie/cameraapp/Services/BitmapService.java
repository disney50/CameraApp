package com.example.gelie.cameraapp.Services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapService {

    public static Bitmap decodeSampledBitmapFromString(String filePath) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        options.inSampleSize = 4;

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /*public static Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight) {
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
    }*/

    public static Bitmap downscaleToMaxAllowedDimension(Bitmap bitmap) {
        int MAX_ALLOWED_RESOLUTION = 1024;
        int outWidth;
        int outHeight;
        int inWidth = bitmap.getWidth();
        int inHeight = bitmap.getHeight();
        if(inWidth > inHeight){
            outWidth = MAX_ALLOWED_RESOLUTION;
            outHeight = (inHeight * MAX_ALLOWED_RESOLUTION) / inWidth;
        } else {
            outHeight = MAX_ALLOWED_RESOLUTION;
            outWidth = (inWidth * MAX_ALLOWED_RESOLUTION) / inHeight;
        }

        return Bitmap.createScaledBitmap(bitmap, outWidth, 85, false);
    }
}
