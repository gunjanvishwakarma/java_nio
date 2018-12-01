package com.gunjan.nio.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelExample {
    public static void main(String[] args) throws IOException {
        try (RandomAccessFile aFile = new RandomAccessFile("nio-data.txt", "rw");
             FileChannel inChannel = aFile.getChannel();) {
            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = -1;
            while ((bytesRead = inChannel.read(buf)) != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }

                buf.clear();
            }
        }
    }
}
