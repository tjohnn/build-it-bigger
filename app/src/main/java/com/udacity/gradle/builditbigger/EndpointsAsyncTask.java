package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.tjohnn.jokedisplayer.JokeDetails;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static JokeApi myApiService = null;
    private final OnEndpointAsyncTaskListener onEndpointAsyncTaskListener;
    private Context context;

    public EndpointsAsyncTask(OnEndpointAsyncTaskListener onEndpointAsyncTaskListener) {
        this.onEndpointAsyncTaskListener = onEndpointAsyncTaskListener;
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            // simulate long running task
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.tellAJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        onEndpointAsyncTaskListener.onDone();
        Intent intent = new Intent(context, JokeDetails.class);
        intent.putExtra(JokeDetails.JOKE_EXTRA_KEY, result);
        context.startActivity(intent);
    }

    // For making callback notifications to the user of this class
    public interface OnEndpointAsyncTaskListener{
        void onDone();
    }
}