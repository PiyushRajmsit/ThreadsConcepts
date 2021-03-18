package com.CriticalSection;

import java.util.Random;

public class ThreadSafeAtomicOperations {
    public static void main(String[] args){
        Metrics metrics = new Metrics();

        Thread buisnessLogicThread1 = new Thread(new BuisnessLogic(metrics));
        Thread buisnessLogicThread2 = new Thread(new BuisnessLogic(metrics));
        Thread metricsPrinterThread = new Thread(new MetricsPrinter(metrics));

        buisnessLogicThread1.start();
        buisnessLogicThread2.start();
        metricsPrinterThread.start();

    }

    public static class MetricsPrinter implements Runnable{

        private Metrics metrics;

        public MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                double currentAverage = metrics.getAverage();
                System.out.println("The Average Time is :" + currentAverage);
            }
        }
    }


    public static class BuisnessLogic implements Runnable{
        private Metrics metrics;
        private Random random = new Random();

        public BuisnessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true){

                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                metrics.addSample(end-start);
            }
        }
    }



    public static class Metrics{
        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample){
            double currentSum = count * average;
            count++;
            average = (currentSum + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }


}
