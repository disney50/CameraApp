package com.example.gelie.cameraapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.Vector;

class ImageAdapter extends BaseAdapter {

    Context mContext;
    Vector<ImageView> capturedImages;

    public ImageAdapter (Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
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

        mImageView.setImageResource(mThumbIds[position]);
        return mImageView;
    }

    private Integer[] mThumbIds = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground
    };
}
