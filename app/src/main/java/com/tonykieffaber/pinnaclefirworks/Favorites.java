package com.tonykieffaber.pinnaclefirworks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {

    private ArrayList<String> _favorites = new ArrayList<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        String empty = "Favorites List is Empty!";

        if(_favorites.isEmpty()) {
            _favorites.add(empty);
        }

        ListView listView = findViewById(R.id.uxFavoritesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,_favorites);





            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });

    }






    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){

    }
}
