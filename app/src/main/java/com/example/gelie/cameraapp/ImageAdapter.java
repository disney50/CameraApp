package com.example.gelie.cameraapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.gelie.cameraapp.Services.BitmapService;
import com.example.gelie.cameraapp.Services.GeneralServices;

import java.util.ArrayList;

class ImageAdapter extends BaseAdapter {

    private ArrayList<String> mFolder;
    private LayoutInflater mInflater;

    ImageAdapter(Context context, ArrayList<String> folder) {
        mFolder = folder;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.gallery_item, null);
            holder.imageView = convertView.findViewById(R.id.image_thumbnail);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bitmap bitmap = BitmapService.decodeSampledBitmapFromString(mFolder.get(position));

        holder.imageView.setImageBitmap(BitmapService.downscaleToMaxAllowedDimension(bitmap));
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }

}
