package com.gunjan.nio.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatterAndGatherNIO {
    public static void main(String[] args) throws IOException {
        readRequest();
        writeResponse();

    }

    private static void readRequest() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile("request.txt", "rw");
             FileChannel fileChannel = file.getChannel()) {
            ByteBuffer header = ByteBuffer.allocate(70);
            ByteBuffer body = ByteBuffer.allocate(108);
            ByteBuffer[] byteBuffers = new ByteBuffer[]{header, body};
            fileChannel.read(byteBuffers);

            header.flip();
            body.flip();

            System.out.println("====HEADER====");
            while (header.hasRemaining()) {
                System.out.print((char) header.get());
            }
            System.out.println();
            System.out.println("====BODY====");
            while (body.hasRemaining()) {
                System.out.print((char) body.get());
            }
        }
    }

    private static void writeResponse() throws IOException {
        try (
                RandomAccessFile file = new RandomAccessFile("response.txt", "rw");
                FileChannel fileChannel = file.getChannel()
        ) {
            ByteBuffer header = ByteBuffer.allocate(70);
            header.put("Header".getBytes());
            header.flip();

            ByteBuffer body = ByteBuffer.allocate(108);
            body.put("Body".getBytes());
            body.flip();

            ByteBuffer[] byteBuffers = new ByteBuffer[]{header, body};

            fileChannel.write(byteBuffers);
        }
    }
}
