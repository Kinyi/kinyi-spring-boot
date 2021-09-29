package com.zhss.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Kinyi_Chan
 * @since 2021-09-28
 */
public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    private GroupChatServer() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8989));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void listen() throws IOException {
        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println(socketChannel.getRemoteAddress() + "上线");
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    int len;
                    while ((len = socketChannel.read(byteBuffer)) > 0) {
                        System.out.println("from客户端: " + new String(byteBuffer.array(), 0, len));
                        for (SelectionKey key : selector.keys()) {
                            Channel channel = key.channel();
                            if (channel instanceof SocketChannel && channel != socketChannel) {
                                byteBuffer.flip();
                                ((SocketChannel) channel).write(byteBuffer);
                            }
                        }
                        byteBuffer.clear();
                    }
                    if (len < 0) {
                        System.out.println(socketChannel.getRemoteAddress() + "离线了");
                        selectionKey.cancel();
                        socketChannel.close();
                    }
                }
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new GroupChatServer().listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
