package com.dev.myapplication;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Devesh Chaturvedi on 014-14-10-2017.
 */

public class TriviaActivity extends AppCompatActivity implements LoaderCallbacks<String> {

    private static final String REQUEST_URL = "http://numbersapi.com";
    private static final int LOADER_ID = 1;
    String category;
    String query;
    String query2;
    private TextView mEmptyStateTextView;
    private TextView mTrivia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trivia_layout);

        category = getIntent().getStringExtra("category");
        query = getIntent().getStringExtra("query");

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        mTrivia = (TextView) findViewById(R.id.trivia);


        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading_indicator);
            progressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);

        }

    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {

        Uri baseUri = Uri.parse(REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        if (query.equalsIgnoreCase("rubbish")) {

            uriBuilder.appendPath("/random");
            uriBuilder.appendPath("/" + category);
        } else {
            uriBuilder.appendPath("/" + query);
            uriBuilder.appendPath("/" + category);
        }


        return new TriviaLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String trivia) {
        // Clear the adapter of previous earthquake data

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading_indicator);
        progressBar.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_trivia);
        // If there is a valid list of trivias, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (trivia != null && !trivia.isEmpty()) {
            mEmptyStateTextView.setText(null);
            mTrivia.setText(trivia);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        // Loader reset, so we can clear out our existing data.
        mTrivia.setText(null);
    }
}
