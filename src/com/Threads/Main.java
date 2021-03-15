package com.Threads;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
	// write your code here
    LongComputationTask longComputationTask = new LongComputationTask(BigInteger.TEN,BigInteger.valueOf(1000000000));
    Thread thread = new Thread(longComputationTask);
    thread.start();
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();// Just thread Interrupt doesnt Works

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
                if(Thread.currentThread().isInterrupted())// Needs to be added along with thread interrupt
                {
                    System.out.println("Thread Prematurely Interupted on Power :" + i);
                    return BigInteger.ZERO;
                }
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
