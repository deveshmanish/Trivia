package com.dev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Devesh Chaturvedi on 027-27-10-2017.
 */

public class SpecificTrivia extends AppCompatActivity {

    String query;
    String category;
    EditText editText;
    Button button;
    DatePicker datePicker;
    String day = null, month = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_fact_layout);

        category = getIntent().getStringExtra("category");
        editText = (EditText) findViewById(R.id.query);
        editText.setTransformationMethod(null);
//        editText.setHint("Enter something");
        button = (Button) findViewById(R.id.query_submit);
        datePicker = (DatePicker) findViewById(R.id.date_picker);
        switch (category) {
            case "date": {

                editText.clearFocus();
                editText.setVisibility(View.GONE);
                datePicker.setVisibility(View.VISIBLE);
                break;
            }
            default: {
                editText.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.GONE);
                editText.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        editText.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);

                        if (s == "") {
                            Toast t = Toast.makeText(SpecificTrivia.this, "Please enter a value", Toast.LENGTH_LONG);
                            t.show();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        query = editText.getText().toString().trim();
                    }
                });
            }
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category.equalsIgnoreCase("date")) {
                    day = String.valueOf(datePicker.getDayOfMonth());
                    month = String.valueOf(datePicker.getMonth() + 1);
                }
                Intent intent = new Intent(SpecificTrivia.this, TriviaActivity.class);
                intent.putExtra("category", category);
                switch (category) {
                    case "date": {
                        query = month + "/" + day;
                        intent.putExtra("query", query);
                        break;
                    }
                    default:
                        intent.putExtra("query", query);

                }
                if (query == "" || query == null) {
                    Toast t = Toast.makeText(SpecificTrivia.this, "Please enter a value", Toast.LENGTH_LONG);
                    t.show();
                } else
                    startActivity(intent);

            }
        });

    }
}
