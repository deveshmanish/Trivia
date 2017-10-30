package com.dev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    RecyclerView recyclerView;
    private Category_Recycler_View_Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        updateTheme();

        List<Category> category = loadCategory();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new Category_Recycler_View_Adapter(category, getApplication(), new OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.theme) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public List<Category> loadCategory() {
        List<Category> categories = new ArrayList<>();
        Category category;
        category = new Category("Trivia");
        categories.add(category);
        category = new Category("Math");
        categories.add(category);
        category = new Category("Date");
        categories.add(category);
        category = new Category("Year");
        categories.add(category);

        return categories;
    }
}
