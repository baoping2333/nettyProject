package com.ping.nio;

import java.nio.IntBuffer;

/**
 * @program: nettyProject
 * @ClassName BasicBuffer
 * @description:
 * @author: wanbaoping
 * @create: 2021-09-13 12:40
 * @Version 1.0
 **/
public class BasicBuffer {
    public static void main(String[] args) {
        //可以存放5个Int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        //取出数据
        //将Buffer转换，读写切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}

