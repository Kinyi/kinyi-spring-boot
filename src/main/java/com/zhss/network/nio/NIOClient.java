package com.zhss.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author Kinyi_Chan
 * @since 2021-09-23
 */
public class NIOClient {

    private void produce() throws Exception {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8989));
        socketChannel.write(ByteBuffer.wrap("hello".getBytes()));
        socketChannel.close();
    }

    private void client() {
        try {
            Selector selector = Selector.open();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("localhost", 8989));
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    if (selectionKey.isConnectable()) {
                        if (channel.isConnectionPending()) {
                            // 必须要调用finishConnect方法才能完成连接
                            if (channel.finishConnect()) {
                                channel.write(ByteBuffer.wrap("client connected!".getBytes()));
                                channel.register(selector, SelectionKey.OP_READ);
                            } else {
                                selectionKey.cancel();
                            }
                        }
                    } else if (selectionKey.isReadable()) {
                        byteBuffer.clear();
                        int len = channel.read(byteBuffer);
                        byteBuffer.flip();
                        String content = new String(byteBuffer.array(), 0, len);
                        System.out.println(content);
                        channel.register(selector, SelectionKey.OP_WRITE);
                    } else if (selectionKey.isWritable()) {
                        byteBuffer.clear();
                        channel.write(ByteBuffer.wrap("client content".getBytes()));
                        channel.register(selector, SelectionKey.OP_READ);
                    } else {
                        selectionKey.channel().close();
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new NIOClient().client();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
