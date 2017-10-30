package com.dev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Devesh Chaturvedi on 027-27-10-2017.
 */

public class SpecificTrivia extends BaseActivity {

    String query;
    String category;
    EditText editText;
    Button button;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_fact_layout);

        category = getIntent().getStringExtra("category");
        editText = (EditText) findViewById(R.id.query);
        editText.setTransformationMethod(null);
        button = (Button) findViewById(R.id.query_submit);
        getSupportActionBar().setTitle(category.toUpperCase());
        button.setEnabled(false);


        editText.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                query = editText.getText().toString().trim();

                switch (category) {
                    case "date": {
                        if (TextUtils.isEmpty(query) || query.length() != 4) {
                            button.setEnabled(false);
                        } else if (formateDate(query).equals("")) {
                            button.setEnabled(false);
                            Toast t = Toast.makeText(SpecificTrivia.this, "Please enter a valid value in MMDD format)", Toast.LENGTH_SHORT);
                            t.show();
                        } else {
                            query = formateDate(query);
                            button.setEnabled(true);
                        }
                        break;
                    }
                    default: {
                        if (TextUtils.isEmpty(query)) {
                            button.setEnabled(false);
                            Toast t = Toast.makeText(SpecificTrivia.this, "Please enter a value", Toast.LENGTH_SHORT);
                            t.show();
                        } else {
                            button.setEnabled(true);
                        }
                    }
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SpecificTrivia.this, TriviaActivity.class);
                intent.putExtra("category", category);
                intent.putExtra("query", query);
                startActivity(intent);
                finish();

            }
        });

    }

    public String formateDate(String input) {

        int maxdays[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String day = null, month = null;
        int d, m;
        if (input.length() == 4) {

            month = input.substring(0, 2);
            day = input.substring(2, 4);
            d = Integer.parseInt(day);
            m = Integer.parseInt(month);

            if (m < 0 || m > 12 || d < 0 || d > maxdays[m]) // Performing Date Validation
            {
                input = "";
            } else input = month + "/" + day;

        }
        return input;
    }
}
