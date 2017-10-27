package com.dev.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Devesh Chaturvedi on 014-14-10-2017.
 */

public class Category_View_Holder extends RecyclerView.ViewHolder {

    CardView category;
    RelativeLayout expandedView;
    TextView categoryTitle;
    Button random;
    Button specific;

    Category_View_Holder(View itemView) {
        super(itemView);
        category = (CardView) itemView.findViewById(R.id.categorycardView);
        categoryTitle = (TextView) itemView.findViewById(R.id.category);
        expandedView = (RelativeLayout) itemView.findViewById(R.id.expanded_menu);
        random = (Button) itemView.findViewById(R.id.random_button);
        specific = (Button) itemView.findViewById(R.id.specific_button);
    }

}