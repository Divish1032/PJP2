package com.sapient.calculator.web;

import java.util.Scanner;

 public class CommonMethod {
    
    public static void sop(final String s) {
        System.out.println(s);
    }

    public static void sopSL(final String s) {
        System.out.print(s);
    }

    public static int inputChoice(final Scanner d) {
        sop("Enter your choice: ");
        return  d.nextInt();
    }

    public final static void clearConsole()
    {
        sop("\033[H\033[2J");
        System.out.flush();
    }

}