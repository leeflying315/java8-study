package com.lifei.study.selectorstudy;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class ChatServer implements Cloneable
{
    private static String BYE_BYE = "再见";

    public static void main(String[] args)
        throws IOException,
        InterruptedException
    {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 9527));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while (true)
        {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext())
            {
                SelectionKey selectionKey = iter.next();
                if (selectionKey.isAcceptable())
                {
                    register(selector, serverSocketChannel);
                }
                if (selectionKey.isReadable())
                {
                    serverResponse(byteBuffer, selectionKey);
                }
                iter.remove();
            }
            Thread.sleep(1000);
        }
    }

    private static void serverResponse(ByteBuffer byteBuffer, SelectionKey selectionKey)
        throws IOException
    {
        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
        socketChannel.read(byteBuffer);
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes).trim());
        if (new String(bytes).trim().equals(BYE_BYE))
        {
            System.out.println("说再见不如不见！");
            socketChannel.write(ByteBuffer.wrap("再见".getBytes()));
            socketChannel.close();
        }
        else
        {
            socketChannel.write(ByteBuffer.wrap("你是个好人".getBytes()));
        }
        byteBuffer.clear();
    }

    private static void register(Selector selector, ServerSocketChannel serverSocketChannel)
        throws IOException
    {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }
}
