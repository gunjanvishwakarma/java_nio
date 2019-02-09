package com.gunjan.nio.example;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileSystemTest
{
    public static void main(String[] args)
    {
        FileSystem fs = FileSystems.getDefault();
    
        System.out.println("Read-only file system: " + fs.isReadOnly());
        System.out.println("File name separator: " + fs.getSeparator());
    
        for (FileStore store : fs.getFileStores()) {
            printDetails(store);
        }
        for (Path root : fs.getRootDirectories()) {
            System.out.println(root);
        }
    }
    
    public static void printDetails(FileStore store) {
        try {
            String desc = store.toString();
            String type = store.type();
            long totalSpace = store.getTotalSpace();
            long unallocatedSpace = store.getUnallocatedSpace();
            long availableSpace = store.getUsableSpace();
            System.out.println(desc + ", Total: " + totalSpace + ",  Unallocated: "
                    + unallocatedSpace + ",  Available: " + availableSpace + "   Type: "+type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
