package com.tonykieffaber.pinnaclefirworks;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ItemDescriptionPage extends AppCompatActivity {

    private Catalog _cat = new Catalog();
    private int _item = _cat.get_itemChosen();

    private int _spinnerPosition;

    private ArrayList<String>_name = new ArrayList<>();
    private ArrayList<String>_description = new ArrayList<>();
    private ArrayList<String>_videoLink = new ArrayList<>();
    private ArrayList<Integer>_imagesArrayNumber = new ArrayList<>();
    private ArrayList<String> _categoryDescription = new ArrayList<>();
    private ArrayList<String> _categoryVideoLink = new ArrayList<>();
    private ArrayList<Integer> _categoryImagesArrayNumber = new ArrayList<>();
    private ArrayList<String> _categoryNames = new ArrayList<>();
    private ArrayList<Integer> _category = new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description_page);
        TextView pageText = findViewById(R.id.uxFireworkLabel);
        ImageView imageView = findViewById(R.id.uxFireworkImage);
        pageText.setPaintFlags(pageText.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
        TextView tv = findViewById(R.id.uxDescriptionLabel);
        tv.setPaintFlags(tv.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Intent i= getIntent();

        final Integer position = i.getExtras().getInt("POSITION");
        Integer spinner = i.getExtras().getInt("SPINNERPOSITION");
        _item=position;
        _spinnerPosition=spinner;
        ReadFile();

        CategorySort(_spinnerPosition);
        Log.d("SpinnerPosition", Integer.toString(_spinnerPosition));



        pageText.setText(_categoryNames.get(_item));
        imageView.setImageResource(_categoryImagesArrayNumber.get(_item));
        TextView description = findViewById(R.id.uxDescription);
        description.setText(_categoryDescription.get(_item));

        Log.d("VideoStreamed",_categoryVideoLink.get(_item));









        Button button = findViewById(R.id.uxVideoButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(_categoryVideoLink.get(_item)));
                startActivity(intent);
            }
        });







        final ToggleButton toggleButton = findViewById(R.id.uxFavoritesToggle);
        toggleButton.setChecked(false);
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.gray_star_transparent));
        toggleButton.setText("");
        toggleButton.setTextOff("");
        toggleButton.setTextOn("");



        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yellow_star_transparent));

                } else {
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gray_star_transparent));

                }
            }
        });

    }









    public void ReadFile(){

        InputStream is = getResources().openRawResource(R.raw.fireworks);
        BufferedReader reader = new BufferedReader( new InputStreamReader(is,Charset.forName("UTF-8")));
        String line="";


        try{
            reader.readLine();
            while ((line=reader.readLine())!=null){
                String[] tokens= line.split(",");
                _category.add(Integer.parseInt(tokens[0]));
                _name.add(tokens[1]);
                _description.add(tokens[4]);
                _videoLink.add(tokens[5]);
                _imagesArrayNumber.add(this.getResources().getIdentifier(tokens[6],"drawable",this.getPackageName()));

            }





        }
        catch (IOException e)
        {
            Log.wtf("Catalog", "Error reading data file on line" + line, e);
            e.printStackTrace();

        }

    }


    public void CategorySort(int position){

        _categoryNames.clear();


        _categoryImagesArrayNumber.clear();
        if(position==0){
            for(int i =0;i<_name.size();i++){
                _categoryNames.add(_name.get(i));
                _categoryImagesArrayNumber.add(_imagesArrayNumber.get(i));
                _categoryDescription.add(_description.get(i));
                _categoryVideoLink.add(_videoLink.get(i));
            }
        }

        for(int i =0;i<_name.size();i++){
            if(_category.get(i)==position){
                _categoryNames.add(_name.get(i));
                _categoryImagesArrayNumber.add(_imagesArrayNumber.get(i));
                _categoryDescription.add(_description.get(i));
                _categoryVideoLink.add(_videoLink.get(i));

            }
        }
    }



}
