package com.sunteng.openglsample.sample02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * OpenGLSample
 * Created by baishixian on 2017/8/18.
 */

public class OpenGLSample02Activity extends AppCompatActivity{

    MyGLSurfaceView2 mGLSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("OpenGLSample02");

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLSurfaceView = new MyGLSurfaceView2(this);
        setContentView(mGLSurfaceView);
    }
}
