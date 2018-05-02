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

    public static Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight) {
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
    }
}
