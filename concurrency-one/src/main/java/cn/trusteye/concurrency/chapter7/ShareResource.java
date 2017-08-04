package cn.trusteye.concurrency.chapter7;

import java.util.Random;

public class ShareResource {
    private char[] resource = "abcdefgh".toCharArray();
    Random random = new Random(System.currentTimeMillis());

    public void read(){

    }

    public void write(){

    }
}
