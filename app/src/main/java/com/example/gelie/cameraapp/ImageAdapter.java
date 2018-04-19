package com.example.gelie.cameraapp;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;

class ImageAdapter extends BaseAdapter {

    private Context mContext;

    File root = new File(Environment.getExternalStorageDirectory() + File.separator + "CameraApp" + File.separator);

    private File[] fileName = root.listFiles();

    public ImageAdapter (Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return fileName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Uri uri = Uri.fromFile(fileName[position]);

        ImageView mImageView;

        if (convertView == null) {
            mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(8, 8, 8, 8);
        }
        else {
            mImageView = (ImageView) convertView;
        }

        mImageView.setImageURI(uri);
        return mImageView;
    }
}
