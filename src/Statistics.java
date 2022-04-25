package src;

import java.util.ArrayList;

public class Statistics {
    int totalDelay;
    int avgQueuLength;
    int maxQueuLength;
    int totalQueueLengthSum;
    ArrayList<Integer> queueLengths;
    int maxDelay = 0;
    public Statistics() {
        this.totalDelay = 0;
        this.avgQueuLength = 0;
        this.maxQueuLength = 0;
        this.totalQueueLengthSum = 0;
        this.queueLengths = new ArrayList<>();
    }

    public void newStats(int newLength) {
        this.queueLengths.add(newLength);
        this.totalQueueLengthSum += newLength;
        if (newLength > this.maxQueuLength){
            this.maxQueuLength = newLength;
        }
    }

    public int getAvgQueueLength() {
        if (this.queueLengths.size() == 0)
            return this.totalQueueLengthSum;
        return this.totalQueueLengthSum / this.queueLengths.size();
    }

    public void addDelay(int delay){
        if (delay > maxDelay) maxDelay = delay;
        this.totalDelay = this.totalDelay + delay;
    }

}
