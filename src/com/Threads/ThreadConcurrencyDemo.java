package com.Threads;

public class ThreadConcurrencyDemo {

    public static void main (String [] args) throws InterruptedException {
        Microsoft microsoft = new Microsoft();
        Google google = new Google();
        Facebook facebook = new Facebook();

        Long start = System.currentTimeMillis();
        google.start();
        facebook.start();
        microsoft.start();

        microsoft.join();
        google.join();
        facebook.join();

        Long end = System.currentTimeMillis();
        Long total = end - start;

        System.out.println("Data Combined -->" + microsoft.getData() + " , " + google.getData() + " , " + facebook.getData() + " , " );
        System.out.println("Total Time ==>" + total);

    }

    public static class Microsoft extends Thread{
        private Boolean success = false;
        private String data = "Empty";
        public Microsoft(){

        }

        @Override
        public void run(){

            try {
                Thread.sleep(550);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            success = true;
            data = "Microsoft";
        }

        public String getData() {
            return data;
        }

        public Boolean getSuccess() {
            return success;
        }
    }
    public static class Google extends Thread{
        private Boolean success = false;
        private String data = "Empty";
        public Google(){}

        @Override
        public void run(){

            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            success = true;
            data = "Google";
        }

        public String getData() {
            return data;
        }

        public Boolean getSuccess() {
            return success;
        }
    }
    public static class Facebook extends Thread{
        private Boolean success = false;
        private String data = "Empty";
        public Facebook(){}

        @Override
        public void run(){

            try {
                Thread.sleep(450);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            success = true;
            data = "Facebook";
        }

        public String getData() {
            return data;
        }

        public Boolean getSuccess() {
            return success;
        }
    }

}
