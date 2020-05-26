package com.ostrov.quakereport;

import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

/** Loads a list of earthquakes by using an AsyncTask to perform the network request to the given URL. */
class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    /** kick off loading data */
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /** This is on a background thread. */
    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == null)
            return null;

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}