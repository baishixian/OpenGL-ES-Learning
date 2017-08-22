package com.sunteng.openglsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sunteng.openglsample.sample01.OpenGLSample01Activity;
import com.sunteng.openglsample.sample02.OpenGLSample02Activity;
import com.sunteng.openglsample.sample03.OpenGLSample03Activity;
import com.sunteng.openglsample.sample04.OpenGLSample04Activity;
import com.sunteng.openglsample.sample05.OpenGLSample05Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goSample01(View view) {
        startActivity(new Intent(this, OpenGLSample01Activity.class));
    }

    public void goSample02(View view) {
        startActivity(new Intent(this, OpenGLSample02Activity.class));
    }

    public void goSample03(View view) {
        startActivity(new Intent(this, OpenGLSample03Activity.class));
    }

    public void goSample04(View view) {
        startActivity(new Intent(this, OpenGLSample04Activity.class));
    }

    public void goSample05(View view) {
        startActivity(new Intent(this, OpenGLSample05Activity.class));
    }
}
