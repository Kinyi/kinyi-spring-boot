package com.zhss.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Kinyi_Chan
 * @since 2021/9/24 22:56
 */
public class MultiThreadEchoServerReactor {

    private ServerSocketChannel serverSocketChannel;
    private AtomicInteger next = new AtomicInteger(0);
    private Selector bossSelector;
    private Reactor bossReactor;
    //selectors集合,引入多个selector选择器
    private Selector[] workSelectors = new Selector[2];
    //引入多个子反应器
    private Reactor[] workReactors;

    private MultiThreadEchoServerReactor() throws IOException {
        bossSelector = Selector.open();
        //初始化多个selector选择器
        workSelectors[0] = Selector.open();
        workSelectors[1] = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress("localhost", 8989);
        serverSocketChannel.socket().bind(address);
        //非阻塞
        serverSocketChannel.configureBlocking(false);

        //第一个selector,负责监控新连接事件
        SelectionKey sk = serverSocketChannel.register(bossSelector, SelectionKey.OP_ACCEPT);
        //附加新连接处理handler处理器到SelectionKey（选择键）
        sk.attach(new AcceptorHandler());

        //处理新连接的反应器
        bossReactor = new Reactor(bossSelector);

        //第一个子反应器，一子反应器负责一个选择器
        Reactor subReactor1 = new Reactor(workSelectors[0]);
        //第二个子反应器，一子反应器负责一个选择器
        Reactor subReactor2 = new Reactor(workSelectors[1]);
        workReactors = new Reactor[]{subReactor1, subReactor2};
    }

    private void startService() {
        new Thread(bossReactor).start();
        // 一个子反应器对应一条线程
        new Thread(workReactors[0]).start();
        new Thread(workReactors[1]).start();
    }

    //反应器
    class Reactor implements Runnable {
        //每条线程负责一个选择器的查询
        final Selector selector;

        Reactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    //单位为毫秒
                    selector.select(1000);
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    if (null == selectedKeys || selectedKeys.size() == 0) {
                        continue;
                    }
                    Iterator<SelectionKey> it = selectedKeys.iterator();
                    while (it.hasNext()) {
                        //Reactor负责dispatch收到的事件
                        SelectionKey sk = it.next();
                        dispatch(sk);
                        it.remove();
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        void dispatch(SelectionKey sk) {
            Runnable handler = (Runnable) sk.attachment();
            //调用之前attach绑定到选择键的handler处理器对象
            if (handler != null) {
                handler.run();
            }
        }
    }

    // Handler:新连接处理器
    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
                System.out.println("接收到一个新的连接");

                if (channel != null) {
                    int index = next.get();
                    System.out.println("选择器的编号：" + index);
                    Selector selector = workSelectors[index];
                    new MultiThreadEchoHandler(selector, channel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (next.incrementAndGet() == workSelectors.length) {
                next.set(0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MultiThreadEchoServerReactor server = new MultiThreadEchoServerReactor();
        server.startService();
    }
}
