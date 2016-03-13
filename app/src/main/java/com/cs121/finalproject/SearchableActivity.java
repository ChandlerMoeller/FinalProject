package com.cs121.finalproject;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SearchableActivity extends Activity {

    DBHandler db = new DBHandler(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            TextView searchExplanationText = (TextView) findViewById(R.id.searchTextView);
            searchExplanationText.setText("Dining Halls which are currently serving "+ query.toUpperCase() +":");
            ArrayList<ArrayList<List<MenuItem>>> cacheitems;
            cacheitems = db.getCacheOneItems().get(1);
            for(int i = 0; i <= 4; i++){
                for(int j = 0; j <= 2; j++){
                  for(int k = 0; k<= cacheitems.get(i).get(j).size()-1; k++) {
                      if (cacheitems.get(i).get(j).get(k).name.toLowerCase().equals(query.toLowerCase())) {

                          //display dining hall at the top
                          //diplay meal (i.e. breakfast, lunch, or dinner) next

                          //display other dining halls that have the food



                          //MiddleFragment testfrag = new MiddleFragment();
                          //testfrag.Menusview(3);

                      }
                  }
                }
            }
        }
    }

}