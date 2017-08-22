package com.sunteng.openglsample.sample02;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * OpenGLSample
 * Created by baishixian on 2017/8/18.
 */

public class Square {

    /**
     * 顶点坐标数据缓冲区（float 类型）
     */
    private FloatBuffer vertexBuffer;

    /**
     * 绘制顺序数据缓冲区（short类型）
     */
    private ShortBuffer drawListBuffer;


    /**
     * 顶点坐标数据的数组
     */
    static float squareCoords[] = {
            -0.5f,  0.5f, 0.0f,   // top left
            -0.5f, -0.5f, 0.0f,   // bottom left
            0.5f, -0.5f, 0.0f,   // bottom right
            0.5f,  0.5f, 0.0f }; // top right

    /**
     * 绘制顶点顺序
     */
    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices


    public Square() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
    }
}
