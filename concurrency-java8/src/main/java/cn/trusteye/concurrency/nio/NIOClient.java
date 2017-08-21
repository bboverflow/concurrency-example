package cn.trusteye.concurrency.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {
    private static final int blockSize = 4096;
    private static ByteBuffer sendBuff = ByteBuffer.allocate(blockSize);
    private static ByteBuffer recvBuff = ByteBuffer.allocate(blockSize);
    private int flag;

    private static final InetSocketAddress isa = new InetSocketAddress("127.0.0.1",12345);

    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        Selector selector = Selector.open();

        sc.register(selector, SelectionKey.OP_CONNECT);
        sc.connect(isa);

        Set<SelectionKey> selectionKeys ;
        Iterator<SelectionKey> iterator;
        SelectionKey selectionKey;

        SocketChannel client;
        String recvText;
        String SendText;


        while (true){
            selectionKeys = selector.selectedKeys();
            iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                selectionKey = iterator.next();
                if(selectionKey.isConnectable()){
                    client = (SocketChannel) selectionKey.channel();
                    if(client.isConnectionPending()){
                        client.finishConnect();
                        System.out.println("client connet");
                        sendBuff.clear();

                        sendBuff.put("Hello Server".getBytes());
                        sendBuff.flip();
                        client.write(sendBuff);
                    }
                }else if(selectionKey.isReadable()){

                }
            }
        }
    }
}
