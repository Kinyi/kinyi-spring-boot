package com.zhss.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kinyi_Chan
 * @since 2021-09-22
 */
public class NIOServer {

    private void choose() throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8989));
        // 设置该通道是非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = socketChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        String content = new String(byteBuffer.array(), 0, len);
                        System.out.println(Thread.currentThread().getName() + " -> " + content);
                        Thread.sleep(10 * 1000);
                    }
                }
                iterator.remove();
            }
        }
    }

    /**
     * 简单的将SelectionKey分发到线程池时有问题的, 有可能同一个SelectionKey会被多个线程使用
     * 应该将SelectionKey跟固定的线程绑定
     *
     * @throws Exception
     */
    @Deprecated
    private void chooseThread() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8989));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    executorService.submit(new Handler(socketChannel));
                }
                iterator.remove();
            }
        }
    }

    class Handler extends Thread {
        private SocketChannel socketChannel;

        public Handler(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void run() {
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int len;
                while ((len = socketChannel.read(byteBuffer)) > 0) {
                    byteBuffer.flip();
                    String content = new String(byteBuffer.array(), 0, len);
                    System.out.println(Thread.currentThread().getName() + " --> " + content);
                    Thread.sleep(10 * 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private ByteBuffer byteBuffer;

    private Selector selector;

    public NIOServer() throws Exception {
        byteBuffer = ByteBuffer.allocate(1024);
        selector = Selector.open();
    }

    private void server() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
//            serverSocketChannel.bind(new InetSocketAddress(8989), 2);
            serverSocketChannel.socket().bind(new InetSocketAddress(8989), 2);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (selector.select() > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    handleKey(selectionKey);
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleKey(SelectionKey selectionKey) {
        try {
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                byteBuffer.clear();
                int len = socketChannel.read(byteBuffer);
                byteBuffer.flip();
                String content = new String(byteBuffer.array(), 0, len);
                System.out.println(content);
                Thread.sleep(500);
                socketChannel.register(selector, SelectionKey.OP_WRITE);
            } else if (selectionKey.isWritable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                socketChannel.write(ByteBuffer.wrap("200 server received".getBytes()));
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else {
                selectionKey.channel().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new NIOServer().server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
