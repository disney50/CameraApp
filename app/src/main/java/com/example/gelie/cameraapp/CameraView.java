package com.example.gelie.cameraapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.gelie.cameraapp.Services.GeneralServices;

import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by mardus on 2018/04/05.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback
{

    private Camera mCamera;

    public CameraView(Context context, Camera camera)
    {
        super(context);
        mCamera = camera;

        SurfaceHolder sHolder = getHolder();
        sHolder.addCallback(this);
    }

    public void restartCameraView() {
        mCamera.stopPreview();
        mCamera.startPreview();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {
            Camera.Parameters parameters = mCamera.getParameters();

            List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
            Camera.Size previewSize = previewSizes.get(0);
            for (int i = 0; i < previewSizes.size(); i++) {
                if (previewSizes.get(i).width > previewSize.width)
                    previewSize = previewSizes.get(i);
            }
            parameters.setPreviewSize(previewSize.width, previewSize.height);

            List<Camera.Size> pictureSizes = parameters.getSupportedPictureSizes();
            Camera.Size pictureSize = pictureSizes.get(0);
            for(int i=0;i<pictureSizes.size();i++)
            {
                if(pictureSizes.get(i).width > pictureSize.width)
                    pictureSize = pictureSizes.get(i);
            }
            parameters.setPictureSize(pictureSize.width, pictureSize.height);

            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);

            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                parameters.set("orientation", "landscape");
                mCamera.setDisplayOrientation(0);
                parameters.setRotation(0);
            }
            else {
                parameters.set("orientation", "portrait");
                mCamera.setDisplayOrientation(90);
                parameters.setRotation(90);
            }

            mCamera.setParameters(parameters);
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
            //IO moet by gevoeg word word!
            } catch (IOException e)
                {
                Log.d(GeneralServices.TAG, "TODO" + e.getMessage());
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
