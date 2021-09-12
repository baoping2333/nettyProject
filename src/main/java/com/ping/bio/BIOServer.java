package com.ping.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {
//        创建线程池
        ExecutorService CachedThreadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动");
        while (true) {
//            监听
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            //如果有客户端连接，就创建一个线程与之通讯
            CachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {//重写
                    hander(socket);
                }
            });
        }
    }

    //Handerler方法和客户端通讯
    public static void hander(Socket socket) {
        try {
            System.out.println("线程信息"+ Thread.currentThread().getId()+"  "+Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            //循环读取数据
            while (true) {
                System.out.println("线程信息"+ Thread.currentThread().getId()+"  "+Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if (read != -1) {
                    //输出数据
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭了连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
