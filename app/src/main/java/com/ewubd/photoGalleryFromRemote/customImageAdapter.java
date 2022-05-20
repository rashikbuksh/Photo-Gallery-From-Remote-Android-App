package com.ewubd.photoGalleryFromRemote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

public class customImageAdapter extends BaseAdapter {

    Context context;
    ArrayList<photosArrayList> arrayList;
    String photoURL, desc;
    public customImageAdapter(Context context, ArrayList<photosArrayList> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("ViewHolder")
        View rowView = inflater.inflate(R.layout.gridcustomview, parent, false);

        ImageView img = rowView.findViewById(R.id.imagefromRemote);

        photosArrayList photosArrayList = arrayList.get(position);


        photoURL = photosArrayList.getImageID();
        desc = photosArrayList.getDescription();
        Picasso.get().load("https://muthosoft.com/univ/photos/"+photosArrayList.getImageID()).resize(1000,1000).into(img);


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, showImage.class);
                intent.putExtra("imageID", photosArrayList.getImageID());
                intent.putExtra("imageDes", photosArrayList.getDescription());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
