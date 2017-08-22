package com.sunteng.openglsample.sample05;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * OpenGLSample
 * Created by baishixian on 2017/8/21.
 */

public class MyGLSurfaceView05 extends GLSurfaceView {

    private final MyGLRenderer5 mRenderer;

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    public MyGLSurfaceView05(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        // Create a GLRenderer instance
        mRenderer = new MyGLRenderer5();

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);

        // 可选选项，是将渲染模式设置为仅在你的绘制数据发生变化时才在视图中进行绘制操作
        // 如果选用这一配置选项，那么除非调用了requestRender()，否则GLSurfaceView不会被重新绘制，这样做可以让应用的性能及效率得到提高。

        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        // getX/getY 触摸点相对于其所在view组件坐标系的坐标（以触摸点所在 view 的左上角为原点的坐标系）
        // getRawX/getRawY 触摸点相对于屏幕默认坐标系的坐标（以屏幕的左上角为原点的坐标系）
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX; // 从左往有滑动时: x 值增大，dx 为正；反之则否。
                float dy = y - mPreviousY; // 从上往下滑动时: y 值增大，dy 为正；反之则否。

                // OpenGL 绕 z 轴的旋转符合左手定则，即 z 轴朝屏幕里面为正。
                // 用户面对屏幕时，是从正面向里看（此时 camera 所处的 z 坐标位置为负数），当旋转度数增大时会进行逆时针旋转。

                // 逆时针旋转判断条件1：触摸点处于 view 水平中线以下时，x 坐标应该要符合从右往左移动，此时 dx 是减小的，所以 dx 要变成负数。
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // 逆时针旋转判断条件2：触摸点处于 view 竖直中线以左时，y 坐标应该要符合从下往上移动，此时 dy 是减小的，所以 dy 要变成负数。
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }

                mRenderer.setAngle(mRenderer.getAngle() + ((dx + dy) * TOUCH_SCALE_FACTOR));

                // 在计算旋转角度后，调用requestRender()来告诉渲染器现在可以进行渲染了
                requestRender();
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}
