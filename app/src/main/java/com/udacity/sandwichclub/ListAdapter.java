package com.udacity.sandwichclub;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.sandwichclub.model.Sandwich;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public class ListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Sandwich> sandwichList;

    public ListAdapter(Context context, List<Sandwich> sandwiches) {
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.sandwichList = sandwiches;
    }

    @Override
    public int getCount() {
        return sandwichList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.single_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.aNametxt);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.appIconIV);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.txtName.setText(sandwichList.get(position).getMainName());
        viewHolder.imageURL = sandwichList.get(position).getImage();
        new DownloadAsyncTask().execute(viewHolder);
        return convertView;
    }

    private static class ViewHolder {
        TextView txtName;
        ImageView icon;
        String imageURL;
        Bitmap bitmap;
    }

    public class DownloadAsyncTask  extends AsyncTask<ViewHolder, Void, ViewHolder> {

        @Override
        protected ViewHolder doInBackground(ViewHolder... params) {
            ViewHolder viewHolder = params[0];
            try {
                URL imageURL = new URL(viewHolder.imageURL);
                viewHolder.bitmap = BitmapFactory.decodeStream(imageURL.openStream());
            } catch (IOException e) {
                viewHolder.bitmap = null;
            }
            return viewHolder;
        }

        @Override
        protected void onPostExecute(ViewHolder result) {
            if (result.bitmap == null) {
                result.icon.setImageResource(R.drawable.sandwicj_icon);
            } else {
                result.icon.setImageBitmap(result.bitmap);
            }

        }
    }

}

