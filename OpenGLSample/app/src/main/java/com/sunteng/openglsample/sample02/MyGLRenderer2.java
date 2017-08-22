package com.sunteng.openglsample.sample02;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * OpenGLSample
 * Created by baishixian on 2017/8/18.
 */

public class MyGLRenderer2 implements GLSurfaceView.Renderer {

    private Triangle mTriangle;
    private Square mSquare;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // initialize a triangle
        mTriangle = new Triangle();
        // initialize a square
        mSquare = new Square();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        mTriangle.draw();
    }

    /**
     * 加载并编译着色器代码
     * @param type 渲染器类型 {GLES20.GL_VERTEX_SHADER, GLES20.GL_FRAGMENT_SHADER}
     * @param shaderCode 渲染器代码 GLSL
     * @return
     */
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
