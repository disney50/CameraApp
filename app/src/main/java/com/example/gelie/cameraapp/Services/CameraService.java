package com.example.gelie.cameraapp.Services;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;

public class CameraService
{

    public static boolean canIAccessTheCamera(Context context)
    {
        //check if device has camera
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public static Camera getCameraInstance()
    {
        Camera camera = null;
        try {

            camera = Camera.open();
        }
        catch (Exception e) {
            Log.d(GeneralServices.TAG, "Exception: " + e.getMessage());
        }
        return camera;
    }
}
