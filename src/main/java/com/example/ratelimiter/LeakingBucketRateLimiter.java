package com.example.ratelimiter;
import java.util.LinkedList;
import java.util.Queue;

public class LeakingBucketRateLimiter implements RateLimiter {
    private final long capacity;
    private final long leakRate;
    private final Queue<String> queue;


    public LeakingBucketRateLimiter(long capacity, long leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.queue = new LinkedList<>();

        new Thread(()->{
            while(true){
                try {
                    Thread.sleep(1000/leakRate);
                    if(!queue.isEmpty()){
                        String req = queue.poll();
                        System.out.println("Request " + req + " allowed");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    public synchronized boolean allowRequest(String clientId) {
        if (this.queue.size() >= this.capacity) {
            return false;
        }
        queue.offer(clientId);
        return true;
    }
}
