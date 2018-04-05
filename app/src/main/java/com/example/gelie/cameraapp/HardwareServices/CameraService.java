package com.example.gelie.cameraapp.HardwareServices;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

public class CameraService
{

    public static boolean CanIAccessTheCamera(Context context)
    {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            // this device has a camera
            return true;
        }
        else
        {
            // no camera on this device
            return false;
        }
    }

    public static Camera getCameraInstance()
    {

        Camera camera = null;
        try {

            camera = Camera.open();
        }
        catch (Exception exception) {
            String ass = "SDFA";
        }
        return camera;

    }

}
