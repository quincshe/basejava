package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {

    public static void main(String[] args) {
        String filePath = "./.gitignore";

//        File file = new File(filePath);
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }

        File dir = new File("/home/quincshe/JAVAOPS/basejava/src");
//        System.out.println(dir.isDirectory());
        readDirectory(dir, 0);

//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private static void readDirectory(File dir, int count) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                printTabs(count);
                System.out.println(file.getName());
                if (file.isDirectory()) {
                    readDirectory(file, count + 1);
                }
            }
        }
    }

    private static void printTabs(int c) {
        for (int i = 0; i < c; i++) {
            System.out.print("\t");
        }
    }
}