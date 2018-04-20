package com.example.gelie.cameraapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

class ImageAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<String> mFolder;

    public ImageAdapter (Context context, ArrayList<String> folder) {
        mContext = context;
        this.mFolder = folder;

    }

    @Override
    public int getCount() {
        return mFolder.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Uri uri = Uri.fromFile(fileName[position]);

        ImageView mImageView = (ImageView) convertView;

        if (mImageView == null) {
            mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(8, 8, 8, 8);
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mFolder.get(position), options);

        options.inSampleSize = 4;

        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(mFolder.get(position), options);

        mImageView.setImageBitmap(bitmap);
        return mImageView;
    }
}
