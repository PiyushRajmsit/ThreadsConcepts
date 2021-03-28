package com.CriticalSection;

public class SyncronizedRaceConditionSolution {

    public static void main(String[] args) throws InterruptedException {

        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();
        incrementingThread.join();
        decrementingThread.join();

        System.out.println("The Value is:" + inventoryCounter.getItems());

    }


    public static class IncrementingThread extends Thread{
        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }
        @Override
        public void run(){
            for(int i=0;i<1000000;i++)
                inventoryCounter.increment();
        }

    }

    public static class DecrementingThread extends Thread{
        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }
        @Override
        public void run(){
            for(int i=0;i<1000000;i++)
                inventoryCounter.decrement();
        }
    }



    public static class InventoryCounter{
        private int items = 0;

        public InventoryCounter() {
            items = 0;
        }

        public int getItems() {
            return items;
        }

        public synchronized void increment(){
            items++;
        }
        public synchronized void decrement(){
            items--;
        }


    }


}
