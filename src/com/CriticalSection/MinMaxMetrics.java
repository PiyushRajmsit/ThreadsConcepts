package com.CriticalSection;

public class MinMaxMetrics {
    private volatile long min;
    private volatile long max;
    public MinMaxMetrics() {
        // Add code here
        min = Long.MAX_VALUE;
        max = Long.MIN_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public synchronized void addSample(long newSample) {
        min = Math.min(min,newSample);
        max = Math.max(max,newSample);
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return min;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return max;
    }


}
