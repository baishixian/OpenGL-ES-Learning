package com.sunteng.openglsample.sample01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * OpenGLSample
 * Created by baishixian on 2017/8/17.
 */

public class OpenGLSample01Activity extends AppCompatActivity {

    MyGLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("OpenGLSample01");

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLSurfaceView = new MyGLSurfaceView(this);
        setContentView(mGLSurfaceView);
    }
}
