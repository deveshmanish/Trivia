package com.dev.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private Category_Recycler_View_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
