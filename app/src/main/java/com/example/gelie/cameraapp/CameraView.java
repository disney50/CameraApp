package com.example.gelie.cameraapp;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Created by mardus on 2018/04/05.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback
{

    private SurfaceHolder sHolder;
    private Camera mCamera;

    public CameraView(Context context, Camera camera)
    {
        super(context);
        mCamera = camera;

        sHolder = getHolder();
        sHolder.addCallback(this);
    }

    public void restartCameraView() {
        mCamera.stopPreview();
        mCamera.startPreview();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        Camera.Parameters parameters = mCamera.getParameters();

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            parameters.set("orientation", "landscape");
            mCamera.setDisplayOrientation(90);
            parameters.setRotation(90);
        }
        else {
            parameters.set("orientation", "portrait");
            mCamera.setDisplayOrientation(0);
            parameters.setRotation(0);
        }

        mCamera.setParameters(parameters);

        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
            //IO moet by gevoeg word word!
            } catch (IOException e)
                {
                Log.d(TAG, "TODO" + e.getMessage());
                }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {

    }
}
