package com.bill.test;

public class MainTestHarness {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
        LoanSystem loanSystem = new LoanSystem();
    }

    public void init() {
    }
}