package com.zhss.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Kinyi_Chan
 * @since 2021-09-28
 */
public class GroupChatClient {

    private Selector selector;
    private static SocketChannel socketChannel;

    public GroupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(8989));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println(socketChannel.getLocalAddress() + " is ok");
    }

    private void listen() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    int len = socketChannel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array(), 0, len));
                    byteBuffer.clear();
                }
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Thread thread = new Thread(() -> {
                try {
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        if (scanner.hasNextLine()) {
                            String input = scanner.nextLine();
                            socketChannel.write(ByteBuffer.wrap(input.getBytes()));
                        } else {
                            Thread.sleep(500);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();
            new GroupChatClient().listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
