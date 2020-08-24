package com.sapient.transactions;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        Runner run = new Runner();
        try {
            run.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}