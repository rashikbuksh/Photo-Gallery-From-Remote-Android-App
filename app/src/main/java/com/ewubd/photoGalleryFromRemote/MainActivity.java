package com.ewubd.photoGalleryFromRemote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.GridView;
import android.widget.ListAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private GridView photoGrid;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    customImageAdapter customImageAdapter;
    String data;
    ArrayList<photosArrayList> arrayList;
    private String URL = "https://muthosoft.com/univ/photos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photoGrid = findViewById(R.id.photoGrid);
        arrayList = new ArrayList<>();
        httpRequest();
    }
    @SuppressLint("StaticFieldLeak")
    private void httpRequest(){
        executorService.execute( ()->{
            try{
                System.out.println("parsing Json");
                data = JSONParser.getInstance().makeHttpRequest(URL, "POST");
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            handler.post(()->{
                if(data != null){
                    try{
                        String[] splitComma = data.split(",");
                        ArrayList<photosArrayList> arrayList = new ArrayList<>();
                        for (String splitter : splitComma){
                            String[] splitColon = splitter.split(":");
                            photosArrayList imagesArrayList = new photosArrayList(splitColon[0], splitColon[1]);
                            arrayList.add(imagesArrayList);
                        }
                        loadData(arrayList);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
        });
    }
    void loadData(ArrayList arrayList){
        customImageAdapter = new customImageAdapter(this,arrayList);
        photoGrid.setAdapter(customImageAdapter);
        customImageAdapter.notifyDataSetChanged();
    }
}