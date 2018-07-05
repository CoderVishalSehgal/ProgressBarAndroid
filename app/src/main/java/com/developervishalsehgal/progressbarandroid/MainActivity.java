package com.developervishalsehgal.progressbarandroid;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int PROGRESS_DELAY = 100;
    private static final int MAX_PROGRESS = 100;

    private ProgressBar mDeterminateProgress;
    private ProgressBar mDeterminateStyledProgress;

    private ProgressBar mBufferedProgress;
    private ProgressBar mBufferedStyledProgress;

    private ProgressBar mMultiProgress;
    private ProgressBar mMultiStyledProgress;

    Handler mHandler;
    private int progress;
    private int secondaryProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        fillProgressBars();


    }

    private void initViews(){
        mHandler = new Handler();
        mDeterminateProgress = findViewById(R.id.determinateProgress);
        mDeterminateStyledProgress = findViewById(R.id.determinateStyledProgress);

        mBufferedProgress = findViewById(R.id.bufferedProgress);
        mBufferedStyledProgress = findViewById(R.id.bufferedStyledProgress);

        mMultiProgress =  findViewById(R.id.multiProgress);
        mMultiStyledProgress =  findViewById(R.id.multiStyledProgress);
    }

    /**
     * You can make your Algo. for the progress of ProgressBar.
     */

    private void fillProgressBars() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int newProgress = getNewProgress();
                progress += newProgress;
                secondaryProgress += (newProgress + 2);

                if (progress > 100) {
                    progress = 0;
                }

                if (secondaryProgress > 100) {
                    secondaryProgress = 0;
                }

                mDeterminateProgress.setProgress(progress);
                mDeterminateStyledProgress.setProgress(progress);

                mBufferedProgress.setProgress(progress);
                mBufferedStyledProgress.setProgress(progress);

                mMultiProgress.setProgress(progress);
                mMultiStyledProgress.setProgress(progress);

                if (secondaryProgress <= MAX_PROGRESS) {
                    mBufferedProgress.setSecondaryProgress(secondaryProgress);
                    mBufferedStyledProgress.setSecondaryProgress(secondaryProgress);
                }

                if (progress <= MAX_PROGRESS) {
                    fillProgressBars();
                } else {
                    mMultiProgress.setIndeterminate(true);
                    mMultiStyledProgress.setIndeterminate(true);
                }

            }
        }, PROGRESS_DELAY);
    }

    private int getNewProgress() {
        Random random = new Random();
        return random.nextInt(5);
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

}
