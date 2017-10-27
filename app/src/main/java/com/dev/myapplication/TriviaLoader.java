package com.dev.myapplication;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by Devesh Chaturvedi on 014-14-10-2017.
 */

public class TriviaLoader extends AsyncTaskLoader<String> {

    String Url;

    public TriviaLoader(Context context, String url) {
        super(context);
        Url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        if (Url.length() < 1 || Url == null) {
            return null;
        }
        String result = TriviaQuery.fetchTriviaData(Url);
        return result;
    }
}
