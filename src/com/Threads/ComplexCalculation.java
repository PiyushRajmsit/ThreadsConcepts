package com.Threads;

import java.math.BigInteger;

public class ComplexCalculation {


    public static void main(String[] args){

        BigInteger result = calculateResult(BigInteger.valueOf(3),BigInteger.valueOf(4),BigInteger.valueOf(2),BigInteger.valueOf(10));
        System.out.println(result);
    }
    public static BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1,power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2,power2);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = thread1.getResult().add(thread2.getResult());

        return result;
    }


    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            this.result = calculatePower(base,power);
        }

        private BigInteger calculatePower(BigInteger base,BigInteger power){
            BigInteger temp = BigInteger.ONE;
            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0;i=i.add(BigInteger.ONE)){
                temp = temp.multiply(base);
            }
            return temp;
        }

        public BigInteger getResult() { return result; }
    }
}