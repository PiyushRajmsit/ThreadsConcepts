package com.Threads;

import java.math.BigInteger;

public class Daemon {


    public static void main(String[] args) {
        // write your code here
        Main.LongComputationTask longComputationTask = new Main.LongComputationTask(BigInteger.TEN,BigInteger.valueOf(1000000000));
        Thread thread = new Thread(longComputationTask);
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

    }
    public static class LongComputationTask implements Runnable{

        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        public BigInteger pow(BigInteger base,BigInteger power){
            BigInteger result = BigInteger.ONE;
            for(BigInteger i = BigInteger.ZERO;i.compareTo(power) != 0;i = i.add(BigInteger.ONE)){
                result = result.multiply(base);
            }
            return result;
        }

        @Override
        public void run() {
            System.out.println("The Result of base: " + base + " with Power is: " + power + " = " + pow(base,power));
        }
    }
}
