package com.Threads;

import javafx.util.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadsJoin {

    public static void main(String[] args) throws InterruptedException {

        List<Long> inputNumbers = Arrays.asList(0L,3435L,1000000000L,5L,35435L,2324L,4656L,23L,554L);
        List<FactorialThreads> threadList = new ArrayList<>();

        for(Long input: inputNumbers){
            threadList.add(new FactorialThreads(input));
        }

        for(Thread thread : threadList){
            thread.setDaemon(true);
            thread.start();
        }

        for(Thread thread: threadList){
            thread.join(1000);
        }

        for(int i = 0; i < threadList.size(); i++){
            FactorialThreads factorialThreads = threadList.get(i);
            if(factorialThreads.getFinished()){
                System.out.println("Factorials of " + inputNumbers.get(i) + " is :"  + factorialThreads.getResult());
            }
            else{
                System.out.println("The Calculation for " + inputNumbers.get(i) + " is Still in Progress");
            }
        }
    }

    public static class FactorialThreads extends Thread{
        private long inputNumber;
        private Boolean isFinished = false;
        private BigInteger result = BigInteger.ONE;

        public FactorialThreads(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial();
            this.isFinished = true;
        }

        public BigInteger factorial(){
            BigInteger tempResult = BigInteger.ONE;
            for(long i = inputNumber; i>=2; i--){
                tempResult = tempResult.multiply(BigInteger.valueOf(i));
            }
            return tempResult;
        }

        public Boolean getFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
