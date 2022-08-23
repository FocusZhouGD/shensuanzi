package com.example.othertest;

public class FilePathURLTest {




    public static void main(String[] args) {

        String path="tt/testCT/a.txt";

        String pathNew = path.substring(path.lastIndexOf("/") + 1);
        //a.txt
        System.out.println(pathNew);
    }
}
