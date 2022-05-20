package com.ewubd.photoGalleryFromRemote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class showImage extends AppCompatActivity {

    ImageView image;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        image = findViewById(R.id.image);
        text = findViewById(R.id.desc);

        Intent intent = getIntent();

        String imageID = intent.getStringExtra("imageID");
        String des = intent.getStringExtra("imageDes");

        text.setText(des);
        Picasso.get().load("https://muthosoft.com/univ/photos/"+imageID).resize(1000,1000).into(image);

    }
}