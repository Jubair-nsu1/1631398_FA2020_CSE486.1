package com.jubair.nsu.cse486.sec1.lab9.AsyncTaskAndAsynctaskLoader;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MyAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> mResultTextView;

    MyAsyncTask (TextView tv, TextView result){
        mTextView = new WeakReference<>(tv);
        mResultTextView = new WeakReference<>(result);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int number = random.nextInt(11);
        int milli = number * 200;
        publishProgress(milli);
        try {
            Thread.sleep(milli);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Awake after sleeping for " + milli + " milliseconds";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mResultTextView.get().setText("Current sleep time: " + values[0] + " ms");
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
