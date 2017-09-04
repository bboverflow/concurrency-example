package cn.trusteye.concurrency.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private static final int blockSize = 4096;
    private static final int port  = 12345;
    private Selector selector;

    private ByteBuffer sendBuff = ByteBuffer.allocate(blockSize);
    private ByteBuffer recvBuff = ByteBuffer.allocate(blockSize);
    private int flag;

    public NIOServer(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server start -> "+port);

    }

    public void listen() throws IOException {
        while(true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                handleKey(selectionKey);
            }
        }
    }

    private void handleKey(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel ssc;
        SocketChannel sc;
        String recvText;
        String sendText;
        int count = 0;

        if(selectionKey.isAcceptable()){
            ssc = (ServerSocketChannel) selectionKey.channel();
            sc = ssc.accept();
            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_READ);
        }else if(selectionKey.isReadable()){
            sc = (SocketChannel) selectionKey.channel();
            count = sc.read(recvBuff);
            if(count>0){
                recvText = new String(recvBuff.array(),0,count);
                System.out.println("server receive : " + recvText);
                sc.register(selector, selectionKey.OP_WRITE);
            }
        }else if(selectionKey.isWritable()){
            sc = (SocketChannel) selectionKey.channel();
            sendBuff.clear();
            sendText = "msg send to client :"+flag++;
            sendBuff.put(sendBuff.array());
            sendBuff.flip();

            sc.write(sendBuff);
            System.out.println("server send :" + sendText);
        }
    }

    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer(12345);
        server.listen();
    }

}
