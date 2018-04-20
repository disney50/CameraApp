package com.example.gelie.cameraapp.HardwareServices;

import android.os.Environment;

import java.io.File;

public class StorageService {

    public static File GetCameraAppDir() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "CameraApp");
    }
}
