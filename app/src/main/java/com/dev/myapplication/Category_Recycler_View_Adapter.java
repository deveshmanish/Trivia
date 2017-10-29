package com.dev.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by Devesh Chaturvedi on 014-14-10-2017.
 */

public class Category_Recycler_View_Adapter extends RecyclerView.Adapter<Category_View_Holder> {

    private List<Category> list = Collections.emptyList();
    private Context context;
    private int expandedPosition = -1;
    private OnItemClickListener mOnItemClickListener;


    public Category_Recycler_View_Adapter(List<Category> list, Context context, OnItemClickListener onItemClickListener) {
        this.list = list;
        this.context = context;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public Category_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.random_fact_layout, parent, false);
        Category_View_Holder holder = new Category_View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Category_View_Holder holder, final int position) {
        holder.categoryTitle.setText(list.get(position).getmTitle());
        holder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check for an expanded view, collapse if you find one
                if (expandedPosition >= 0) {
                    int prev = expandedPosition;
                    notifyItemChanged(prev);
                }
                // Set the current position to "expanded"
                expandedPosition = holder.getPosition();
                notifyItemChanged(expandedPosition);

            }
        });

        holder.random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TriviaActivity.class);
                intent.putExtra("category", list.get(position).getmTitle().toLowerCase());
                intent.putExtra("query", "random");
                context.startActivity(intent);

            }
        });
        holder.specific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SpecificTrivia.class);
                intent.putExtra("category", list.get(position).getmTitle().toLowerCase());
                context.startActivity(intent);

            }
        });

        if (position == expandedPosition) {
            holder.expandedView.setVisibility(View.VISIBLE);
        } else {
            holder.expandedView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
