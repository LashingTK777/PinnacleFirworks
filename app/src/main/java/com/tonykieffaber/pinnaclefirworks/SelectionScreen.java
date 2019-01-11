package com.tonykieffaber.pinnaclefirworks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectionScreen extends AppCompatActivity {

    String items[] = new String[]{"View Catalog","About Us","Contact Us", "Location","Favorites"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        final ListView listView = findViewById(R.id._selectionScreenList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter((adapter));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String value = listView.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(),value, Toast.LENGTH_SHORT).show();

                if(position==0) {
                    Intent i = new Intent(SelectionScreen.this,Catalog.class );
                    startActivity(i);
                }
                else if(position==1){
                    Intent i = new Intent(SelectionScreen.this,AboutUs.class );
                    startActivity(i);
                }
                else if(position==2){
                    Intent i = new Intent(SelectionScreen.this,ContactUs.class );
                    startActivity(i);
                }
                else if(position==3){
                    Intent i = new Intent(SelectionScreen.this,Location.class );
                    startActivity(i);
                }
                else if(position==4){
                    Intent i = new Intent(SelectionScreen.this,Favorites.class );
                    startActivity(i);
                }

            }
        });

    }

}
