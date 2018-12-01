package com.gunjan.nio.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class NIOChanneltoChannelTransfer {
    public static void main(String[] args) throws IOException {
        try (RandomAccessFile read = new RandomAccessFile("read.txt", "rw");
             RandomAccessFile write = new RandomAccessFile("write.txt", "rw");
             FileChannel readFileChannel = read.getChannel();
             FileChannel writeFileChannel = write.getChannel();
        ) {
            readFileChannel.transferTo(0, 10, writeFileChannel);
        }
    }
}
