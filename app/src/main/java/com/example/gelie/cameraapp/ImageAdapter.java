package com.example.gelie.cameraapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.example.gelie.cameraapp.HardwareServices.GeneralService;

import java.util.ArrayList;

class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mFolder;

    ImageAdapter(Context context, ArrayList<String> folder) {
        mContext = context;
        mFolder = folder;

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

        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else {
            imageView = (ImageView) convertView;
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mFolder.get(position), options);

        options.inSampleSize = 4;

        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(mFolder.get(position), options);

        imageView.setImageBitmap(GeneralService.scaleDownBitmapImage(bitmap, 85, 85));
        return imageView;
    }
}
