package com.sunteng.openglsample.sample02;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * OpenGLSample 定义一个三角形
 * Created by baishixian on 2017/8/18.
 */

class Triangle {

    // 绘制形状的顶点数量
    private static final int COORDS_PER_VERTEX = 3;

    /**
     * 顶点着色器代码
     * attribute变量(属性变量)只能用于顶点着色器中，不能用于片元着色器。一般用该变量来表示一些顶点数据，如：顶点坐标、纹理坐标、颜色等
     * uniforms变量(一致变量)用来将数据值从应用程其序传递到顶点着色器或者片元着色器。 该变量有点类似C语言中的常量（const），即该变量的值不能被shader程序修改。一般用该变量表示变换矩阵、光照参数、纹理采样器等。
     * varying变量(易变变量)是从顶点着色器传递到片元着色器的数据变量。顶点着色器可以使用易变变量来传递需要插值的颜色、法向量、纹理坐标等任意值。 在顶点与片元shader程序间传递数据是很容易的，一般在顶点shader中修改varying变量值，然后片元shader中使用该值，当然，该变量在顶点及片元这两段shader程序中声明必须是一致的。
     * gl_Position 为内建变量，表示变换后点的空间位置。 顶点着色器从应用程序中获得原始的顶点位置数据，这些原始的顶点数据在顶点着色器中经过平移、旋转、缩放等数学变换后，生成新的顶点位置。新的顶点位置通过在顶点着色器中写入gl_Position传递到渲染管线的后继阶段继续处理。
     */
    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +  // 应用程序传入顶点着色器的顶点位置
                    "void main() {" +
                    "  gl_Position = vPosition;" + // 设置此次绘制此顶点位置
                    "}";

    /**
     * 片元着色器代码
     */
    private final String fragmentShaderCode =
            "precision mediump float;" +  // 设置工作精度
                    "uniform vec4 vColor;" +  // 应用程序传入着色器的颜色变量
                    "void main() {" +
                    "  gl_FragColor = vColor;" + // 颜色值传给gl_FragColor内建变量，完成片元的着色
                    "}";

    /**
     * 定义三角形顶点的坐标数据的浮点型缓冲区
     */
    private FloatBuffer vertexBuffer;

    static float triangleCoords[] = {   // 以逆时针顺序;
            0.0f,  0.622008459f, 0.0f,  // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f   // bottom right
    };

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    private final int mProgram;
    
    private int mPositionHandle;
    private int mColorHandle;

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex


    public Triangle(){
        // 初始化形状中顶点坐标数据的字节缓冲区
        // 通过 allocateDirect 方法获取到 DirectByteBuffer 实例
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(
                // 顶点坐标个数 * 坐标数据类型 float 一个是 4 bytes
                triangleCoords.length * 4
        );

        // 设置缓冲区使用设备硬件的原本字节顺序进行读取;
        byteBuffer.order(ByteOrder.nativeOrder());

        // 因为 ByteBuffer 是将数据移进移出通道的唯一方式使用，这里使用 “as” 方法从 ByteBuffer 中获得一个基本类型缓冲区（浮点缓冲区）
        vertexBuffer = byteBuffer.asFloatBuffer();
        // 把顶点坐标信息数组存储到 FloatBuffer
        vertexBuffer.put(triangleCoords);
        // 设置从缓冲区的第一个位置开始读取顶点坐标信息
        vertexBuffer.position(0);

        // 加载编译顶点渲染器
        int vertexShader = MyGLRenderer2.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);

        // 加载编译片元渲染器
        int fragmentShader = MyGLRenderer2.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
    }


    public void draw() {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
