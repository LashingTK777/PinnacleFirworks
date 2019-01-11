package com.tonykieffaber.pinnaclefirworks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Catalog extends AppCompatActivity {

    private ArrayList<String> _names = new ArrayList<>();
    private ArrayList<String> _shotCount= new ArrayList<>();
    private ArrayList<String> _price = new ArrayList<>();
    private int _itemChosen;
    public int get_itemChosen() {
        return _itemChosen;
    }
    private ArrayList<Integer> _category = new ArrayList<>();
    private ArrayList<Integer> _imagesArrayNumber= new ArrayList<>();
    private ArrayList<String> _description = new ArrayList<>();
    private ArrayList<String> _videoLink = new ArrayList<>();
    private String[] categoryList = {"All","Kids","Kids Assortment Packs","Firecrackers","Roman Candles", "Rockets","Saturn Missile","Fountains","Cakes","Artillery", "Z-Cakes","Assortment Packs","Miscellaneous"};
    private ArrayList<String> _categoryNames = new ArrayList<>();
    private ArrayList<String> _categoryShotCount = new ArrayList<>();
    private ArrayList<String> _categoryPrice = new ArrayList<>();
    private ArrayList<Integer> _categoryImagesArrayNumber = new ArrayList<>();
    private ArrayList<String> _categoryDescription = new ArrayList<>();
    private ArrayList<String> _categoryVideoLink= new ArrayList<>();
    private int _count =0;
    private CustomAdapter custom = new CustomAdapter();
    private int _spinnerPosition;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        ReadFile();
        if(_count==0) {
            CategorySort(0);
            _count++;
        }
        Log.d("Count",Integer.toString(_count));


        final ListView listView = findViewById(R.id.uxCatalogListView);
        Spinner spinner = findViewById(R.id.uxSpinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,categoryList);
        spinner.setAdapter(adapter);




        listView.setAdapter((custom));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _spinnerPosition=position;

                custom.clearlistview();
                CategorySort(position);
                //listView.invalidateViews();


                listView.setAdapter(custom);

                //Toast.makeText(getApplicationContext(),position,Toast.LENGTH_SHORT).show();
                Log.d("Spinner",Integer.toString(position));
                Log.d("Spinner", Integer.toString(custom.getCount()));
                for(int i =0; i<_categoryNames.size();i++) {
                    Log.d("Spinner", _categoryNames.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                _itemChosen=position;

                Intent intent = new Intent(Catalog.this,ItemDescriptionPage.class );

                intent.putExtra("SPINNERPOSITION",_spinnerPosition);
                intent.putExtra("POSITION",position);
                startActivity(intent);


            }
        });




    }




    public void ReadFile(){


        InputStream is = getResources().openRawResource(R.raw.fireworks);
        BufferedReader reader = new BufferedReader( new InputStreamReader(is,Charset.forName("UTF-8")));
        String line="";


        try{
            reader.readLine();

            while ((line=reader.readLine())!=null) {
                String[] tokens = line.split(",");
                    _category.add(Integer.parseInt(tokens[0]));
                    _names.add(tokens[1]);
                    _shotCount.add(tokens[2]);
                    _price.add(tokens[3]);
                    _description.add(tokens[4]);
                    _videoLink.add(tokens[5]);
                    _imagesArrayNumber.add(this.getResources().getIdentifier(tokens[6], "drawable", this.getPackageName()));


                    Log.d("Images", tokens[6]);
                    Log.d("Images", Integer.toString(this.getResources().getIdentifier(tokens[6], "drawable", this.getPackageName())));


            }





        }
        catch (IOException e)
        {
            Log.wtf("Catalog", "Error reading data file on line" + line, e);
            e.printStackTrace();

        }

    }




    class CustomAdapter extends BaseAdapter{


        public void clearlistview()
        {
            _categoryNames.clear();
            _categoryShotCount.clear();
            _categoryPrice.clear();
            _categoryImagesArrayNumber.clear();
            _categoryDescription.clear();
            _categoryVideoLink.clear();
            this.notifyDataSetChanged();
        }

        /**
         * How many items are in the data set represented by this Adapter.
         *
         * @return Count of items.
         */
        @Override
        public int getCount() {
            return _categoryNames.size();
        }

        /**
         * Get the data item associated with the specified position in the data set.
         *
         * @param position Position of the item whose data we want within the adapter's
         *                 data set.
         * @return The data at the specified position.
         */
        @Override
        public Object getItem(int position) {
            return null;
        }

        /**
         * Get the row id associated with the specified position in the list.
         *
         * @param position The position of the item within the adapter's data set whose row id we want.
         * @return The id of the item at the specified position.
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * Get a View that displays the data at the specified position in the data set. You can either
         * create a View manually or inflate it from an XML layout file. When the View is inflated, the
         * parent View (GridView, ListView...) will apply default layout parameters unless you use
         * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
         * to specify a root view and to prevent attachment to the root.
         *
         * @param position    The position of the item within the adapter's data set of the item whose view
         *                    we want.
         * @param convertView The old view to reuse, if possible. Note: You should check that this view
         *                    is non-null and of an appropriate type before using. If it is not possible to convert
         *                    this view to display the correct data, this method can create a new view.
         *                    Heterogeneous lists can specify their number of view types, so that this View is
         *                    always of the right type (see {@link #getViewTypeCount()} and
         *                    {@link #getItemViewType(int)}).
         * @param parent      The parent that this view will eventually be attached to
         * @return A View corresponding to the data at the specified position.
         */
        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.custom_layout,null);

            ImageView iv = convertView.findViewById(R.id.uxListImage);
            TextView tvname= convertView.findViewById(R.id.uxListItemName);
            TextView tvcount = convertView.findViewById(R.id.uxListItemCount);
            TextView tvprice = convertView.findViewById(R.id.uxListPrice);

            iv.setImageResource(_categoryImagesArrayNumber.get(position));
            tvname.setText(_categoryNames.get(position));
            tvcount.setText(_categoryShotCount.get(position));
            tvprice.setText(_categoryPrice.get(position));
            return convertView;
        }
    }


    public void CategorySort(int position){

        _categoryNames.clear();
        _categoryShotCount.clear();
        _categoryPrice.clear();
        _categoryImagesArrayNumber.clear();
        if(position==0){
            for(int i =0;i<_names.size();i++){
                    _categoryNames.add(_names.get(i));
                    _categoryShotCount.add(_shotCount.get(i));
                    _categoryPrice.add(_price.get(i));
                    _categoryImagesArrayNumber.add(_imagesArrayNumber.get(i));
                    _categoryDescription.add(_description.get(i));
                    _categoryVideoLink.add(_videoLink.get(i));
            }
        }

        for(int i =0;i<_names.size();i++){
            if(_category.get(i)==position){
                _categoryNames.add(_names.get(i));
                _categoryShotCount.add(_shotCount.get(i));
                _categoryPrice.add(_price.get(i));
                _categoryImagesArrayNumber.add(_imagesArrayNumber.get(i));
                _categoryDescription.add(_description.get(i));
                _categoryVideoLink.add(_videoLink.get(i));
                //Log.d("ItemName",_categoryNames.get(i));
            }
        }
    }



}
