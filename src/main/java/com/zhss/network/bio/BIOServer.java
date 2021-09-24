package com.zhss.network.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Kinyi_Chan
 * @since 2021-09-22
 */
public class BIOServer {

    private void start() throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 8989));

        while (true) {
            Socket socket = serverSocket.accept();
            new Handler(socket).start();
        }
    }

    public static void main(String[] args) {
        try {
            new BIOServer().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                byte[] byteArr = new byte[1024];
                int len;
                while ((len = socket.getInputStream().read(byteArr)) != -1) {
                    String content = new String(byteArr, 0, len);
                    System.out.println(Thread.currentThread().getName() + " -> " + content);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
